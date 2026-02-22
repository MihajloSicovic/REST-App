/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Artikal;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mihajlo
 */
public class MenjanjeCeneArtikla {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        Message response;
        int cena = msg.getIntProperty("cena");
        if (cena <= 0) {
            response = context.createTextMessage("Greska: Cena nije pozitivna!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        int idA = msg.getIntProperty("idA");
        Artikal a = em.find(Artikal.class, idA);
        if (a == null) {
            response = context.createTextMessage("Greska: Artikal ne postoji!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        a.setCena(cena);
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.clear();
        response = context.createTextMessage("Uspesno menjanje cene artikla.");

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
