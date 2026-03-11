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
public class DohvatiArtikleKorisnika {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        int idK = msg.getIntProperty("idK");
        TypedQuery<Artikal> query = em.createNamedQuery("Artikal.findByIdK", Artikal.class);
        query.setParameter("idK", idK);
        
        List<ArtikalModel> result = new ArrayList<>();
        
        for (Artikal a: query.getResultList()) {
            ArtikalModel am = new ArtikalModel(a.getIdA(), a.getNaziv(),
            a.getOpis(), a.getCena(), a.getPopust(), a.getIdK(), a.getIdKat().getIdKat());
            
            result.add(am);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
