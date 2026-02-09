/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Artikal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.ArtikalModel;

/**
 *
 * @author Mihajlo
 */
public class PostavljanjePopustaArtikal {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        Message response;
        int popust = msg.getIntProperty("popust");
        if (popust < 0 || popust > 100) {
            response = context.createTextMessage("Greska: Popust nije validan!");
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
        
        a.setPopust(popust);
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.clear();
        response = context.createTextMessage("Uspesno postavljanje popusta za artikal.");

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
