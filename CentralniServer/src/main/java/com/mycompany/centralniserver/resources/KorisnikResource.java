/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centralniserver.resources;

import dummies.GradDummy;
import dummies.KorisnikDummy;
import entities.Korisnik;
import filters.AuthChecker;
import java.util.Arrays;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import views.KorisnikView;

/**
 *
 * @author Mihajlo
 */
@Path("korisnik")
@Stateless
public class KorisnikResource {
    @Resource(lookup="myConnFactory")
    ConnectionFactory connFactory;
    
    @Resource(lookup="SubTopic")
    Topic myTopic;
    
    @Resource(lookup="ServerQueue")
    Queue myQueue;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getAllKorisnik(@Context HttpHeaders headers) {
        try {
            Response earlyResp = AuthChecker.checkAuth(headers, "administrator");
            if (earlyResp != null) return earlyResp;
            
            JMSContext context = connFactory.createContext();
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub1");
            msg.setIntProperty("Task", 16);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            List<KorisnikView> result = consumer.receiveBody(List.class);
            
            return Response.ok(result).build();
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @PUT
    @Path("{idK}/{dodatak}")
    @Produces(MediaType.TEXT_PLAIN)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response updateStanjeKorisnik(@Context HttpHeaders headers,
            @PathParam("idK") int idK, 
            @PathParam("dodatak") int dodatak) {
        try {
            Response earlyResp = AuthChecker.checkAuth(headers, "administrator");
            if (earlyResp != null) return earlyResp;
            
            JMSContext context = connFactory.createContext();
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub1");
            msg.setIntProperty("Task", 4);
            msg.setIntProperty("idK", idK);
            msg.setIntProperty("dodatak", dodatak);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            String result = consumer.receiveBody(String.class);
            
            return Response.ok(result).build();
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @PUT
    @Path("{idK}/{idG}/{adresa}")
    @Produces(MediaType.TEXT_PLAIN)
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response updateLocationKorisnik(@Context HttpHeaders headers,
            @PathParam("idK") int idK, 
            @PathParam("idG") int idG, 
            @PathParam("adresa") String adresa) {
        try {
            Response earlyResp = AuthChecker.checkAuth(headers, "administrator");
            if (earlyResp != null) return earlyResp;
            
            JMSContext context = connFactory.createContext();
            
            TextMessage msg = context.createTextMessage();
            msg.setStringProperty("Type", "sub1");
            msg.setIntProperty("Task", 5);
            msg.setIntProperty("idK", idK);
            msg.setIntProperty("idG", idG);
            msg.setStringProperty("adresa", adresa);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            String result = consumer.receiveBody(String.class);
            
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
    public Response createKorisnik(@Context HttpHeaders headers, KorisnikDummy kd) {
        try {
            Response earlyResp = AuthChecker.checkAuth(headers, "administrator");
            if (earlyResp != null) return earlyResp;
            
            JMSContext context = connFactory.createContext();
            
            ObjectMessage msg = context.createObjectMessage(kd);
            msg.setStringProperty("Type", "sub1");
            msg.setIntProperty("Task", 3);
            msg.setJMSReplyTo(myQueue);
            
            JMSProducer producer = context.createProducer();
            producer.send(myTopic, msg);
            JMSConsumer consumer = context.createConsumer(myQueue);
            String result = consumer.receiveBody(String.class);
            
            return Response.ok(result).build();
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
