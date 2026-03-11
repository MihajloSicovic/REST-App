/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import models.KorisnikModel;

/**
 *
 * @author Mihajlo
 */
@Provider
public class BasicAuthFilter implements ContainerRequestFilter {
    @Resource(lookup="myConnFactory")
    ConnectionFactory connFactory;
    
    @Resource(lookup="SubTopic")
    Topic myTopic;
    
    @Resource(lookup="SubRepQueue")
    Queue myQueue;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        context.getHeaders().getFirst("Authorization");
        MultivaluedMap<String, String> headers = context.getHeaders();
        if (!headers.containsKey("Authorization")) {
            context.abortWith(
                Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Korisnicko ime i lozinka nisu prosledjeni.")
                    .build()
            );
            return;
        }
        List<String> authHeaders = context.getHeaders().get("Authorization");
        if (authHeaders.isEmpty()) {
            context.abortWith(
                Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Korisnicko ime i lozinka nisu prosledjeni.")
                    .build()
            );
            return;
        }
        String[] authorization = new String(Base64.getDecoder().decode(authHeaders.get(0).replace("Basic ", "")), StandardCharsets.UTF_8).split(":");
        if (authorization.length != 2) {
            context.abortWith(
                Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Pogresno prosledjeno korisnicko ime ili lozinka.")
                    .build()
            );
            return;            
        }
        // To replace
        String username = authorization[0];
        String password = authorization[1];
        
        try (JMSContext contextJMS = connFactory.createContext()) {
            
            TextMessage msg = contextJMS.createTextMessage();
            try {
                msg.setStringProperty("Type", "sub1");
                msg.setIntProperty("Task", 1);
                msg.setStringProperty("korisnickoIme", username);
                msg.setStringProperty("lozinka", password);
                msg.setJMSReplyTo(myQueue);
            } catch (JMSException ex) {
                Logger.getLogger(BasicAuthFilter.class.getName()).log(Level.SEVERE, null, ex);
            }

            JMSConsumer consumer = contextJMS.createConsumer(myQueue);
            while (consumer.receiveNoWait() != null);
            JMSProducer producer = contextJMS.createProducer();
            producer.send(myTopic, msg);

            try {
                KorisnikModel km = consumer.receiveBody(KorisnikModel.class, 10000);

                // Pass headers to resources.
                String roles = "";
                for (String u: km.getUlogaList()) roles += u + ",";
                if (!roles.isEmpty()) roles = roles.substring(0, roles.length() - 1);

                context.getHeaders().add("X-User-ID", String.valueOf(km.getIdK()));
                context.getHeaders().add("X-User-Roles", roles);
            }
            catch (Exception e) {
                context.abortWith(
                    Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("Pogresno korisnicko ime ili lozinka.")
                        .build()
                );
            }
        }
    }
}
