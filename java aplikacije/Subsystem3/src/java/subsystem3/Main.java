/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subsystem3;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Topic;
import services.DohvatiNarudzbineKorisnika;
import services.DohvatiSveNarudzbine;
import services.DohvatiSveTransakcije;
import services.Placanje;

/**
 *
 * @author Mihajlo
 */
public class Main {

    @Resource(lookup="myConnFactory")
    private static ConnectionFactory connFactory;
    
    @Resource(lookup="SubTopic")
    private static Topic myTopic;
      
    @Resource(lookup="transactionQueue")
    private static Queue transQueue;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMSContext context = connFactory.createContext();   
        JMSConsumer consumer = context.createConsumer(myTopic, "Type = 'sub3'");
        
        System.out.println("Subsystem 3 started.");
        
        while(true) {
            try (JMSContext serviceContext = connFactory.createContext()) {
                Message msg = consumer.receive();
                int task = msg.getIntProperty("Task");
                switch(task) {
                    case 14:
                        Placanje.service(msg, serviceContext, myTopic, transQueue);
                        break;
                    case 21:
                        DohvatiNarudzbineKorisnika.service(msg, serviceContext);
                        break;
                    case 22:
                        DohvatiSveNarudzbine.service(msg, serviceContext);
                        break;
                    case 23:
                        DohvatiSveTransakcije.service(msg, serviceContext);
                        break;
                }
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
