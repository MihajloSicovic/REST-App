/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Artikal;
import entities.Korpa;
import entities.StavkaKorpa;
import entities.StavkaKorpaPK;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mihajlo
 */
public class ObrisiArtikalIzKorpe {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        Message response;
        int idK = msg.getIntProperty("idK");
        int redBr = msg.getIntProperty("redBr");
        
        StavkaKorpaPK pk = new StavkaKorpaPK(idK, redBr);
        StavkaKorpa sk = em.find(StavkaKorpa.class, pk);
        
        if (sk == null) {
            response = context.createTextMessage("Greska: Stavka ne postoji!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        Artikal a = sk.getIdA();
        Korpa k = sk.getKorpa();
        
        k.setUkupnaCena(k.getUkupnaCena() - a.getCena() * (100 - a.getPopust()) / 100 * sk.getKolicina());
        
        try {
            em.getTransaction().begin();
            em.persist(k);
            em.remove(sk);
            em.getTransaction().commit();
            response = context.createTextMessage("Artikal uspesno obrisan iz korpe.");
        }
        catch(Exception e) {
            response = context.createTextMessage("Greska: Artikal se ne moze obrisati!");
        }
        
        TypedQuery<StavkaKorpa> query = em.createNamedQuery("StavkaKorpa.findByIdK", StavkaKorpa.class);
        query.setParameter("idK", idK);
        
        if (query.getResultList().isEmpty()) {
            try {
            em.getTransaction().begin();
            em.remove(k);
            em.getTransaction().commit();
            em.clear();
            }
            catch(Exception e) {
                response = context.createTextMessage("Greska: Korpa se ne moze obrisati!");
            }
        }

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
