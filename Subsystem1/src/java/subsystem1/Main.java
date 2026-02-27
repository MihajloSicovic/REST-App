/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subsystem1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import services.DohvatiKorisnika;
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
            try (JMSContext serviceContext = connFactory.createContext()) {
                Message msg = consumer.receive();
                int task = msg.getIntProperty("Task");
                switch(task) {
                    case 1:
                        DohvatiKorisnika.service(msg, serviceContext);
                        break;
                    case 2:
                        KreirajGrad.service(msg, serviceContext);
                        break;
                    case 3:
                        KreirajKorisnika.service(msg, serviceContext);
                        break;
                    case 4:
                        DoplatiKorisniku.service(msg, serviceContext);
                        break;
                    case 5:
                        PromeniAdresuKorisnika.service(msg, serviceContext);
                        break;
                    case 15:
                        DohvatiSveGradove.service(msg, serviceContext);
                        break;
                    case 16:
                        DohvatiSveKorisnike.service(msg, serviceContext);
                        break;
                }
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

}
