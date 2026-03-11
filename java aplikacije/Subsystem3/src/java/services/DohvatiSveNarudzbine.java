/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Narudzbina;
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
import models.NarudzbinaModel;

/**
 *
 * @author Mihajlo
 */
public class DohvatiSveNarudzbine {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem3PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Narudzbina> query = em.createNamedQuery("Narudzbina.findAll", Narudzbina.class);
        
        List<NarudzbinaModel> result = new ArrayList<>();
        
        for (Narudzbina n: query.getResultList()) {
            NarudzbinaModel nm = new NarudzbinaModel(n.getIdN(), n.getUkupnaCena(), 
            n.getVremeKreiranja(), n.getAdresa(), n.getIdG(), n.getIdK());
            
            result.add(nm);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
