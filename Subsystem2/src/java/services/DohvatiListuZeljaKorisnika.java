/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.StavkaZelja;
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
import models.ListaZeljaModel;

/**
 *
 * @author Mihajlo
 */
public class DohvatiListuZeljaKorisnika {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        int idK = msg.getIntProperty("idK");
        TypedQuery<StavkaZelja> query = em.createNamedQuery("StavkaZelja.findByIdK", StavkaZelja.class);
        query.setParameter("idK", idK);
        
        List<ListaZeljaModel> result = new ArrayList<>();
        
        for (StavkaZelja z: query.getResultList()) {
            ListaZeljaModel zm = new ListaZeljaModel(z.getStavkaZeljaPK().getIdK(), 
            z.getStavkaZeljaPK().getIdA(), z.getDatumDodavanja());
            
            result.add(zm);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
