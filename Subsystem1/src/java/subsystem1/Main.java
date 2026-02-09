/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subsystem1;

import entities.Korisnik;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import services.DohvatiSveGradove;
import services.DohvatiSveKorisnike;
import services.DoplatiKorisniku;
import services.KreirajGrad;
import services.KreirajKorisnika;
import services.PromeniAdresuKorisnika;

/**
 *
 * @author Mihajlo
 */
public class Main {
    @Resource(lookup="myConnFactory")
    private static ConnectionFactory connFactory;
    
    @Resource(lookup="SubTopic")
    private static Topic myTopic;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMSContext context = connFactory.createContext();   
        JMSConsumer consumer = context.createConsumer(myTopic, "Type = 'sub1'");
        
        System.out.println("Subsystem 1 started.");
        
        while(true) {
            try {
                Message msg = consumer.receive();
                int task = msg.getIntProperty("Task");
                switch(task) {
                    case 1:
                        // Not implemented
                        break;
                    case 2:
                        KreirajGrad.service(msg, context);
                        break;
                    case 3:
                        KreirajKorisnika.service(msg, context);
                        break;
                    case 4:
                        DoplatiKorisniku.service(msg, context);
                        break;
                    case 5:
                        PromeniAdresuKorisnika.service(msg, context);
                        break;
                    case 15:
                        DohvatiSveGradove.service(msg, context);
                        break;
                    case 16:
                        DohvatiSveKorisnike.service(msg, context);
                        break;
                }
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

}
