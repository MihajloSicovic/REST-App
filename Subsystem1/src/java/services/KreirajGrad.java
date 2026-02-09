/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dummies.GradDummy;
import entities.Grad;
import entities.Korisnik;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mihajlo
 */
public class KreirajGrad {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem1PU");
        EntityManager em = emf.createEntityManager();
        
        GradDummy gd = (GradDummy)((ObjectMessage)msg).getObject();
        
        Grad g = new Grad();
        g.setNaziv(gd.getNaziv());
        
        Message response;
        
        try {
            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
            em.clear();
            response = context.createTextMessage("Grad je uspesno kreiran.");
        }
        catch(Exception e) {
            response = context.createTextMessage("Greska: Grad ne moze biti kreiran!");
        }
        
        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
