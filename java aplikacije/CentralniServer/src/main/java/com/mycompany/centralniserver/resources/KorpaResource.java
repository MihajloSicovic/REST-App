/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centralniserver.resources;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.KorpaModel;

/**
 *
 * @author Mihajlo
 */
@Path("korpa")
@Stateless
public class KorpaResource {
    @Resource(lookup="myConnFactory")
    ConnectionFactory connFactory;
    
    @Resource(lookup="SubTopic")
    Topic myTopic;
    
    @Resource(lookup="SubRepQueue")
    Queue myQueue;
    
    @GET
    @Path("{idK}")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getKorpaByIdK(@Context HttpHeaders headers,
            @PathParam("idK") int idK) {
        try (JMSContext context = connFactory.createContext()) {
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub2");
            msg.setIntProperty("Task", 19);
            msg.setIntProperty("idK", idK);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            List<KorpaModel> result = consumer.receiveBody(List.class, 10000);
            
            return Response.ok(result).build();
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @POST
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response addArtikalToKorpa(@Context HttpHeaders headers, KorpaModel km) {
        try (JMSContext context = connFactory.createContext()) {
            
            ObjectMessage msg = context.createObjectMessage(km);
            msg.setStringProperty("Type", "sub2");
            msg.setIntProperty("Task", 10);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            String result = consumer.receiveBody(String.class, 10000);
            
            return Response.ok(result).build();
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @DELETE
    @Path("{idK}/{redBr}")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response deleteArtikalFromKorpa(@Context HttpHeaders headers, 
            @PathParam("idK") int idK,
            @PathParam("redBr") int redBr) {
        try (JMSContext context = connFactory.createContext()) {
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub2");
            msg.setIntProperty("Task", 11);
            msg.setIntProperty("idK", idK);
            msg.setIntProperty("redBr", redBr);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            String result = consumer.receiveBody(String.class, 10000);
            
            return Response.ok(result).build();
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
