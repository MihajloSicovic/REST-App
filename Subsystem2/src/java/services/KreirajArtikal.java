/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Artikal;
import entities.Kategorija;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.ArtikalModel;

/**
 *
 * @author Mihajlo
 */
public class KreirajArtikal {
    
     public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        ArtikalModel am = (ArtikalModel)((ObjectMessage)msg).getObject();
        
        Message response;
        if (am.getCena() <= 0) {
            response = context.createTextMessage("Greska: Cena nije pozitivna!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        if (am.getPopust() < 0 || am.getPopust() > 100) {
            response = context.createTextMessage("Greska: Popust nije validan!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        Kategorija kat = em.find(Kategorija.class, am.getIdKat());
        if (kat == null) {
            response = context.createTextMessage("Greska: Kategorija ne postoji!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        Artikal a = new Artikal();
        a.setNaziv(am.getNaziv());
        a.setOpis(am.getOpis());
        a.setCena(am.getCena());
        a.setPopust(am.getPopust());
        a.setIdK(am.getIdK());
        a.setIdKat(kat);
        
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            em.clear();
            response = context.createTextMessage("Artikal je uspesno kreiran.");
        }
        catch(Exception e) {
            response = context.createTextMessage("Greska: Artikal ne moze biti kreiran!");
        }
        
        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
