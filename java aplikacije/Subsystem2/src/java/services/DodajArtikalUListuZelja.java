/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Artikal;
import entities.ListaZelja;
import entities.StavkaZelja;
import java.util.Date;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.ListaZeljaModel;

/**
 *
 * @author Mihajlo
 */
public class DodajArtikalUListuZelja {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        Message response;
        ListaZeljaModel lm = msg.getBody(ListaZeljaModel.class);
        int idK = lm.getIdK();
        int idA = lm.getIdA();
        
        Artikal a = em.find(Artikal.class, idA);
        if (a == null) {
            response = context.createTextMessage("Greska: Artikal ne postoji!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        ListaZelja l = em.find(ListaZelja.class, lm.getIdK());
        if (l == null) {
            l = new ListaZelja();
            l.setIdK(idK);
            l.setDatumKreiranja(new Date());
        } 
        
        StavkaZelja sz = new StavkaZelja(idK, idA);
        sz.setArtikal(a);
        sz.setDatumDodavanja(new Date());
        sz.setListaZelja(l);
        
        try {
            em.getTransaction().begin();
            em.persist(l);
            em.persist(sz);
            em.getTransaction().commit();
            em.clear();
            response = context.createTextMessage("Artikal uspesno dodat u listu zelja.");
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
