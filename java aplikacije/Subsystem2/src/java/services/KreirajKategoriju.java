/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Kategorija;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.KategorijaModel;

/**
 *
 * @author Mihajlo
 */
public class KreirajKategoriju {
    
     public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        KategorijaModel km = (KategorijaModel)((ObjectMessage)msg).getObject();
        
        Message response;
        Kategorija k = new Kategorija();
        k.setNaziv(km.getNaziv());
        
        if (km.getIdPotKat() != 0) {
            Kategorija idPotKat = em.find(Kategorija.class, km.getIdPotKat());
            if (idPotKat == null) {
                response = context.createTextMessage("Greska: Cena nije pozitivna!");
                JMSProducer producer = context.createProducer();
                producer.send(msg.getJMSReplyTo(), response);
                em.close();
                emf.close();
                return;
            }
            
            k.setIdPotKat(idPotKat);
        }
        
        try {
            em.getTransaction().begin();
            em.persist(k);
            em.getTransaction().commit();
            em.clear();
            response = context.createTextMessage("Kategorija je uspesno kreirana.");
        }
        catch(Exception e) {
            response = context.createTextMessage("Greska: Kateogrija ne moze biti kreirana!");
        }
        
        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
