/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.ListaZelja;
import entities.StavkaZelja;
import entities.StavkaZeljaPK;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mihajlo
 */
public class ObrisiArtikalIzListeZelja {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        
        Message response;
        int idK = msg.getIntProperty("idK");
        int idA = msg.getIntProperty("idA");
        
        StavkaZeljaPK pk = new StavkaZeljaPK(idK, idA);
        StavkaZelja sz = em.find(StavkaZelja.class, pk);
        
        if (sz == null) {
            response = context.createTextMessage("Greska: Artikal nije na listi!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        ListaZelja l = sz.getListaZelja();
        
        try {
            em.getTransaction().begin();
            em.remove(sz);
            em.getTransaction().commit();
            response = context.createTextMessage("Artikal uspesno obrisan iz liste.");
        }
        catch(Exception e) {
            response = context.createTextMessage("Greska: Artikal se ne moze obrisati!");
        }
        
        TypedQuery<StavkaZelja> query = em.createNamedQuery("StavkaZelja.findByIdK", StavkaZelja.class);
        query.setParameter("idK", idK);
        
        if (query.getResultList().isEmpty()) {
            try {
            em.getTransaction().begin();
            em.remove(l);
            em.getTransaction().commit();
            em.clear();
            }
            catch(Exception e) {
                response = context.createTextMessage("Greska: Lista se ne moze obrisati!");
            }
        }

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
