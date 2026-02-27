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
public class DohvatiKorisnika {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem1PU");
        EntityManager em = emf.createEntityManager();
        
        String korisnickoIme = msg.getStringProperty("korisnickoIme");
        String lozinka = msg.getStringProperty("lozinka");
        
        TypedQuery<Korisnik> query = em.createQuery(
            "SELECT k FROM Korisnik k WHERE k.korisnickoIme = :korisnickoIme AND k.lozinka = :lozinka",
            Korisnik.class
        );
        
        query.setParameter("korisnickoIme", korisnickoIme);
        query.setParameter("lozinka", lozinka);
        
        List<Korisnik> res = query.getResultList();
        Message response;
        
        if (res.isEmpty()) {
            response = context.createObjectMessage(null);
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        Korisnik k = res.get(0);
        
        List<Integer> ulogaIDList = new ArrayList<>();
        List<String> ulogaList = new ArrayList<>();
        for (Uloga u: k.getUlogaList()) {
            ulogaList.add(u.getNaziv());
            ulogaIDList.add(u.getIdU());
        }
        
        KorisnikModel km = new KorisnikModel(k.getIdK(), k.getKorisnickoIme(),
            k.getLozinka(), k.getIme(), k.getPrezime(), k.getAdresa(), 
            k.getStanje(), k.getGrad().getIdG(), ulogaIDList);
        km.setUlogaList(ulogaList);
        
        response = context.createObjectMessage(km);

        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
