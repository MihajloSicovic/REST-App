/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Korisnik;
import entities.Uloga;
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
import models.KorisnikModel;

/**
 *
 * @author Mihajlo
 */
public class DohvatiSveKorisnike {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem1PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Korisnik> query = em.createNamedQuery("Korisnik.findAll", Korisnik.class);
        List<KorisnikModel> result = new ArrayList<>();
        
        for (Korisnik k: query.getResultList()) {
            List<Integer> ulogaList = new ArrayList<>();
            for (Uloga u: k.getUlogaList()) ulogaList.add(u.getIdU());
            
            KorisnikModel kv = new KorisnikModel(k.getIdK(), k.getKorisnickoIme(),
            k.getLozinka(), k.getIme(), k.getPrezime(), k.getAdresa(), 
            k.getStanje(), k.getGrad().getIdG(), ulogaList);
            
            result.add(kv);
        }
        
        Message response = context.createObjectMessage((Serializable)result);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
