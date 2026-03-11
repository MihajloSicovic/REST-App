/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.StavkaKorpa;
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
import models.KorpaModel;

/**
 *
 * @author Mihajlo
 */
public class DohvatiKorpuKorisnika {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        int idK = msg.getIntProperty("idK");
        TypedQuery<StavkaKorpa> query = em.createNamedQuery("StavkaKorpa.findByIdK", StavkaKorpa.class);
        query.setParameter("idK", idK);
        
        List<KorpaModel> result = new ArrayList<>();
        
        for (StavkaKorpa k: query.getResultList()) {
            KorpaModel km = new KorpaModel(k.getStavkaKorpaPK().getIdK(), 
            k.getStavkaKorpaPK().getRedBr(), k.getKolicina(), k.getIdA().getIdA());
            
            result.add(km);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
