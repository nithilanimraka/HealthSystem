/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Nithila
 */
import com.example.dao.BillingDAO;
import com.example.model.Billing;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.exception.UserNotFoundException;

// Resource class to handle HTTP requests related to Billing objects
@Path("/billings")
public class BillingResource {
    
    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingResource.class);
    
    // Data Access Object for interacting with Billing objects
    private BillingDAO billingDAO = new BillingDAO();

    // GET method to retrieve all billings
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> getAllBilling() {
        LOGGER.info("Getting all prescriptions");
        return billingDAO.getAllBilling();
    }

    // GET method to retrieve a billing by ID
    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Billing getBillingById(@PathParam("billingId") int billingId) {
        LOGGER.info("Getting Billing by ID : {}", billingId);
        Billing billing = billingDAO.getBillingById(billingId);
        if(billing != null){
            return billing;
        } else{
            // Throw an exception if billing is not found
            throw new UserNotFoundException("Billing with ID " + billingId + " not found.");
        }
    }

    // POST method to add a new billing
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addBilling(Billing billing) {
        LOGGER.info("Adding new billing");
        billingDAO.addBilling(billing);
        return "Billing added successfully";
    }

    // PUT method to update an existing billing
    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateBilling(@PathParam("billingId") int billingId, Billing updatedBilling) {
        Billing existingBilling = billingDAO.getBillingById(billingId);

        if (existingBilling != null) {
            LOGGER.info("Updating billing with ID : {}", billingId);
            updatedBilling.setId(billingId);
            billingDAO.updateBilling(updatedBilling);
            return "Billing updated successfully";
        } else{
            // Throw an exception if appointment to be updated is not found
            throw new UserNotFoundException("Billing with ID " + billingId + " not found.");
        }
    }

    // DELETE method to delete a billing by ID
    @DELETE
    @Path("/{billingId}")
    public String deleteBilling(@PathParam("billingId") int billingId) {
        Billing billing = billingDAO.getBillingById(billingId);
        if(billing != null){
            LOGGER.info("Deleting billing");
            billingDAO.deleteBilling(billingId);
            return "Billing deleted successfully";
        }
        else{
            // Throw an exception if billing to be deleted is not found
            throw new UserNotFoundException("Billing with ID " + billingId + " not found.");
        }
    }
    
    // GET method to retrieve billings by patient ID
    @GET
    @Path("/patients/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> getBillingsByPatient(@PathParam("patientId") int patientId) {
        LOGGER.info("Getting billings by patient ID : {}",patientId);
        
        // Retrieve all billings
        List<Billing> billings1 = billingDAO.getAllBilling();
        List<Billing> patientBillings = new ArrayList<>();
        // Filter billings by patient ID
        for(Billing billing : billings1){
            if((billing.getPatient()).getId()== patientId){
                patientBillings.add(billing);
            }
        }
        
        return patientBillings;
    }
}
