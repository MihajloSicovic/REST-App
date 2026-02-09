/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Grad;
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
import views.GradView;

/**
 *
 * @author Mihajlo
 */
public class DohvatiSveGradove {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem1PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Grad> query = em.createNamedQuery("Grad.findAll", Grad.class);
        
        List<GradView> result = new ArrayList<>();
        
        for (Grad g: query.getResultList()) {
            GradView gv = new GradView(g.getIdG(), g.getNaziv());
            result.add(gv);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
