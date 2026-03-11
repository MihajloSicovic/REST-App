/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Artikal;
import entities.Korpa;
import entities.StavkaKorpa;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.KorpaModel;

/**
 *
 * @author Mihajlo
 */
public class DodajArtikalUKorpu {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        Message response;
        KorpaModel km = msg.getBody(KorpaModel.class);
        int idK = km.getIdK();
        
        if (km.getKolicina() < 1) {
            response = context.createTextMessage("Greska: Kolicina mora biti pozitivna!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        Artikal a = em.find(Artikal.class, km.getIdA());
        if (a == null) {
            response = context.createTextMessage("Greska: Artikal ne postoji!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        Korpa k = em.find(Korpa.class, km.getIdK());
        if (k == null) {
            k = new Korpa();
            k.setIdK(idK);
            k.setUkupnaCena(0);
        } 
        
        Integer redBr;
        TypedQuery<Integer> q = em.createQuery(
                "SELECT MAX(k.stavkaKorpaPK.redBr) FROM StavkaKorpa k WHERE k.stavkaKorpaPK.idK = :idK", Integer.class);
        q.setParameter("idK", idK);
        
        redBr = q.getSingleResult();
        redBr = redBr == null ? 1 : redBr + 1;
        
        k.setUkupnaCena(k.getUkupnaCena() + a.getCena() * (100 - a.getPopust()) / 100 * km.getKolicina());
        
        StavkaKorpa sk = new StavkaKorpa(idK, redBr);
        sk.setKorpa(k);
        sk.setKolicina(km.getKolicina());
        sk.setIdA(a);
        
        try {
            em.getTransaction().begin();
            em.persist(k);
            em.persist(sk);
            em.getTransaction().commit();
            em.clear();
            response = context.createTextMessage("Artikal uspesno dodat u korpu.");
        }
        catch(Exception e) {
            response = context.createTextMessage("Greska: Artikal ne moze biti dodat!");
        }

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
