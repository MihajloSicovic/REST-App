/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Korisnik;
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
public class DoplatiKorisniku {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem1PU");
        EntityManager em = emf.createEntityManager();
        
        int idK = msg.getIntProperty("idK");
        int dodatak = msg.getIntProperty("dodatak");
        
        Message response;
        Korisnik k = em.find(Korisnik.class, idK);
        if (k == null) {
            response = context.createTextMessage("Greska: Korisnik ne postoji!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        k.setStanje(k.getStanje() + dodatak);
        em.getTransaction().begin();
        em.persist(k);
        em.getTransaction().commit();
        em.clear();
        response = context.createTextMessage("Uspesno izvrsena uplata.");
        
        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
