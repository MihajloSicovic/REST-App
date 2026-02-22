/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Transakcija;
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
import models.TransakcijaModel;

/**
 *
 * @author Mihajlo
 */
public class DohvatiSveTransakcije {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem3PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Transakcija> query = em.createNamedQuery("Transakcija.findAll", Transakcija.class);
        
        List<TransakcijaModel> result = new ArrayList<>();
        
        for (Transakcija t: query.getResultList()) {
            TransakcijaModel tm = new TransakcijaModel(t.getIdT(), t.getPlacenaSuma(),
            t.getVremePlacanja(), t.getIdN().getIdN());
            
            result.add(tm);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
