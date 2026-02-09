/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subsystem2;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import services.DohvatiArtikleKorisnika;
import services.DohvatiSveKategorije;
import services.KreirajArtikal;
import services.KreirajKategoriju;
import services.MenjanjeCeneArtikla;
import services.PostavljanjePopustaArtikal;

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
        JMSConsumer consumer = context.createConsumer(myTopic, "Type = 'sub2'");
        
        System.out.println("Subsystem 2 started.");
        
        while(true) {
            try {
                Message msg = consumer.receive();
                int task = msg.getIntProperty("Task");
                switch(task) {
                    case 6:
                        KreirajKategoriju.service(msg, context);
                        break;
                    case 7:
                        KreirajArtikal.service(msg, context);
                        break;
                    case 8:
                        MenjanjeCeneArtikla.service(msg, context);
                        break;
                    case 9:
                        PostavljanjePopustaArtikal.service(msg, context);
                        break;
                    case 10:
                        // Not implemented
                        break;
                    case 11:
                        // Not implemented
                        break;
                    case 12:
                        // Not implemented
                        break;
                    case 13:
                        // Not implemented
                        break;
                    case 17:
                        DohvatiSveKategorije.service(msg, context);
                        break;
                    case 18:
                        DohvatiArtikleKorisnika.service(msg, context);
                        break;
                    case 19:
                        // Not implemented
                        break;
                    case 20:
                        // Not implemented
                        break;
                }
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
