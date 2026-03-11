/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Narudzbina;
import entities.StavkaNarudzbina;
import entities.Transakcija;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.ArtikalModel;
import models.KorisnikModel;
import models.KorpaModel;

/**
 *
 * @author Mihajlo
 */
public class Placanje {  
    
    private static JMSConsumer consumer;
    
    public static void service(Message msg, JMSContext context, Topic myTopic, Queue transQueue) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem3PU");
        EntityManager em = emf.createEntityManager();
        consumer = context.createConsumer(transQueue);
        while (consumer.receiveNoWait() != null);
        
        KorisnikModel k = msg.getBody(KorisnikModel.class);
        int idK = k.getIdK();
        
        List<KorpaModel> korpa = dohvatiKorpu(idK, context, myTopic, transQueue);
        
        Message response;
        if (korpa.isEmpty()) {
            response = context.createTextMessage("Greska: Korpa je prazna!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            consumer.close();
            em.close();
            emf.close();
            return;
        }
        
        HashMap<Integer, ArtikalModel> artikli = new HashMap<>();
        
        List<ArtikalModel> art = dohvatiArtikle(context, myTopic, transQueue);
        for (ArtikalModel a: art) 
            artikli.put(a.getIdA(), a);
        
        em.getTransaction().begin();
        
        Narudzbina n = new Narudzbina();
        n.setUkupnaCena(0);
        n.setVremeKreiranja(new Date());
        n.setAdresa(k.getAdresa());
        n.setIdG(k.getIdG());
        n.setIdK(idK);
        
        em.persist(n);
        em.flush();
        
        int ukupnaCena = 0;
        int br = 0;
        HashMap<Integer, Integer> uplate = new HashMap<>();
        for (KorpaModel stavka: korpa) {
            ArtikalModel artikal = artikli.get(stavka.getIdA());
            int jedinicnaCena = artikal.getCena() * (100 - artikal.getPopust()) / 100;
            int ukupno = jedinicnaCena * stavka.getKolicina();
            ukupnaCena += ukupno;
            
            if (uplate.containsKey(artikal.getIdK())) {
                int oldVal = uplate.get(artikal.getIdK());
                uplate.replace(artikal.getIdK(), oldVal + ukupno);
            }
            else uplate.put(artikal.getIdK(), ukupno);
            
            StavkaNarudzbina sn = new StavkaNarudzbina(n.getIdN(), ++br);
            sn.setIdA(stavka.getIdA());
            sn.setKolicina(stavka.getKolicina());
            sn.setJedinicnaCena(jedinicnaCena);
            sn.setNarudzbina(n);
            em.persist(sn);
        }
        
        if (ukupnaCena > k.getStanje()) {
            response = context.createTextMessage("Greska: Nemate dovoljno sredstava!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.getTransaction().rollback();
            consumer.close();
            em.close();
            emf.close();
            return;
        }
        
        n.setUkupnaCena(ukupnaCena);
        em.persist(n);
        
        for (KorpaModel stavka: korpa) 
            obrisiStavku(stavka.getIdK(), stavka.getRedBr(), context, myTopic, transQueue);
        
        Transakcija t = new Transakcija();
        t.setIdN(n);
        t.setPlacenaSuma(ukupnaCena);
        t.setVremePlacanja(new Date());
        
        em.persist(t);
        em.getTransaction().commit();
        em.clear();
        em.close();
        emf.close();  
        
        dodajNovacKorisniku(idK, -ukupnaCena, context, myTopic, transQueue);        
        uplate.forEach((idKor, dodatak) -> {
            try {
                dodajNovacKorisniku(idKor, dodatak, context, myTopic, transQueue);
            }
            catch (JMSException ex) {}
        });
        
        consumer.close();
        
        response = context.createTextMessage("Transakcija izvrsena.");
        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
    }
    
    private static List<KorpaModel> dohvatiKorpu(int idK, JMSContext context, Topic myTopic, Queue transQueue) throws JMSException {
        TextMessage msg = context.createTextMessage();
        msg.setStringProperty("Type", "sub2");
        msg.setIntProperty("Task", 19);
        msg.setIntProperty("idK", idK);
        msg.setJMSReplyTo(transQueue);

        JMSProducer producer = context.createProducer();
        producer.send(myTopic, msg);
        
        return consumer.receiveBody(List.class);
    }
    
    private static List<ArtikalModel> dohvatiArtikle(JMSContext context, Topic myTopic, Queue transQueue) throws JMSException {
        TextMessage msg = context.createTextMessage();
        msg.setStringProperty("Type", "sub2");
        msg.setIntProperty("Task", 24);
        msg.setJMSReplyTo(transQueue);

        JMSProducer producer = context.createProducer();
        producer.send(myTopic, msg);
        
        return consumer.receiveBody(List.class);
    }
    
    private static void dodajNovacKorisniku(int idK, int dodatak, JMSContext context, Topic myTopic, Queue transQueue) throws JMSException {
        TextMessage msg = context.createTextMessage();
        msg.setStringProperty("Type", "sub1");
        msg.setIntProperty("Task", 4);
        msg.setIntProperty("idK", idK);
        msg.setIntProperty("dodatak", dodatak);
        msg.setJMSReplyTo(transQueue);

        JMSProducer producer = context.createProducer();
        producer.send(myTopic, msg);
        
        consumer.receive();
    }
    
    private static void obrisiStavku(int idK, int redBr, JMSContext context, Topic myTopic, Queue transQueue) throws JMSException {
        TextMessage msg = context.createTextMessage();
        msg.setStringProperty("Type", "sub2");
        msg.setIntProperty("Task", 11);
        msg.setIntProperty("idK", idK);
        msg.setIntProperty("redBr", redBr);
        msg.setJMSReplyTo(transQueue);

        JMSProducer producer = context.createProducer();
        producer.send(myTopic, msg);
        
        consumer.receive();
    }
}
