/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centralniserver.resources;

import filters.AuthChecker;
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
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.ArtikalModel;

/**
 *
 * @author Mihajlo
 */
@Path("artikal")
@Stateless
public class ArtikalResource {
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
    public Response getArtikalByIdK(@Context HttpHeaders headers,
            @PathParam("idK") int idK) {
        try (JMSContext context = connFactory.createContext()) {
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub2");
            msg.setIntProperty("Task", 18);
            msg.setIntProperty("idK", idK);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            List<ArtikalModel> result = consumer.receiveBody(List.class, 10000);
            
            return Response.ok(result).build();
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @PUT
    @Path("cena/{idA}/{cena}")
    @Produces(MediaType.TEXT_PLAIN)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response updateArtikalCena(@Context HttpHeaders headers,
            @PathParam("idA") int idA, 
            @PathParam("cena") int cena) {
        try (JMSContext context = connFactory.createContext()) {
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub2");
            msg.setIntProperty("Task", 8);
            msg.setIntProperty("idA", idA);
            msg.setIntProperty("cena", cena);
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
    
    @PUT
    @Path("popust/{idA}/{popust}")
    @Produces(MediaType.TEXT_PLAIN)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response updateArtikalPopust(@Context HttpHeaders headers,
            @PathParam("idA") int idA, 
            @PathParam("popust") int popust) {
        try (JMSContext context = connFactory.createContext()) {
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub2");
            msg.setIntProperty("Task", 9);
            msg.setIntProperty("idA", idA);
            msg.setIntProperty("popust", popust);
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
    
    @POST
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response createArtikal(@Context HttpHeaders headers, ArtikalModel am) {
        try (JMSContext context = connFactory.createContext()) {
            
            ObjectMessage msg = context.createObjectMessage(am);
            msg.setStringProperty("Type", "sub2");
            msg.setIntProperty("Task", 7);
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
