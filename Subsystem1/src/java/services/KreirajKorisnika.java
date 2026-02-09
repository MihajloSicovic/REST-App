/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dummies.GradDummy;
import dummies.KorisnikDummy;
import entities.Grad;
import entities.Korisnik;
import entities.Uloga;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mihajlo
 */
public class KreirajKorisnika {
    
    public static void service(Message msg, JMSContext context) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem1PU");
        EntityManager em = emf.createEntityManager();
        
        KorisnikDummy kd = (KorisnikDummy)((ObjectMessage)msg).getObject();
        
        Message response;
        
        if (kd.getStanje() < 0) {
            response = context.createTextMessage("Greska: Stanje je negativno!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        Grad g = em.find(Grad.class, kd.getIdG());
        if (g == null) {
            response = context.createTextMessage("Greska: Grad ne postoji!");
            JMSProducer producer = context.createProducer();
            producer.send(msg.getJMSReplyTo(), response);
            em.close();
            emf.close();
            return;
        }
        
        List<Uloga> uloge = new ArrayList<>();
        for (int idU: kd.getIdU()) {
            Uloga u = em.find(Uloga.class, idU);
            if (u == null) {
                response = context.createTextMessage("Greska: Uloga ne postoji!");
                JMSProducer producer = context.createProducer();
                producer.send(msg.getJMSReplyTo(), response);
                em.close();
                emf.close();
                return;
            }
            
            uloge.add(u);
        }
        
        Korisnik k = new Korisnik();
        k.setKorisnickoIme(kd.getKorisnickoIme());
        k.setLozinka(kd.getLozinka());
        k.setIme(kd.getIme());
        k.setPrezime(kd.getPrezime());
        k.setAdresa(kd.getAdresa());
        k.setStanje(kd.getStanje());
        k.setGrad(g);
        k.setUlogaList(uloge);
        
        try {
            em.getTransaction().begin();
            em.persist(k);
            em.getTransaction().commit();
            em.clear();
            response = context.createTextMessage("Korisnik je uspesno kreiran.");
        }
        catch(Exception e) {
            response = context.createTextMessage("Greska: Korisnik ne moze biti kreiran!");
        }
        
        JMSProducer producer = context.createProducer();
        producer.send(msg.getJMSReplyTo(), response);
        em.close();
        emf.close();
    }
}
