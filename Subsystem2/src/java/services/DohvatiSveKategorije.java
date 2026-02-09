/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Kategorija;
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
import models.KategorijaModel;

/**
 *
 * @author Mihajlo
 */
public class DohvatiSveKategorije {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Kategorija> query = em.createNamedQuery("Kategorija.findAll", Kategorija.class);
        
        List<KategorijaModel> result = new ArrayList<>();
        
        for (Kategorija kat: query.getResultList()) {
            KategorijaModel km = new KategorijaModel(kat.getIdKat(), kat.getNaziv(), 
            kat.getIdPotKat() == null ? 0 : kat.getIdPotKat().getIdKat());
            
            result.add(km);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
