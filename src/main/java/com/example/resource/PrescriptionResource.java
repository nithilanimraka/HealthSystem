/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Nithila
 */
import com.example.dao.PrescriptionDAO;
import com.example.model.Prescription;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.exception.UserNotFoundException;

// Resource class to handle HTTP requests related to Prescription objects
@Path("/prescriptions")
public class PrescriptionResource {
    
    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);
    
    // Data Access Object for interacting with Prescription objects
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    // GET method to retrieve all prescriptions
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescription() {
        LOGGER.info("Getting all prescriptions");
        return prescriptionDAO.getAllPrescription();
    }

    // GET method to retrieve a prescription by ID
    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        LOGGER.info("Getting Prescription by ID : {}", prescriptionId);
        Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
        if(prescription != null){
            return prescription;
        } else{
            // Throw an exception if prescription is not found 
            throw new UserNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }
    }

    // POST method to add a new prescription
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPrescription(Prescription prescription) {
        LOGGER.info("Adding prescription");
        prescriptionDAO.addPrescription(prescription);
        return "Prescription added successfully";
    }

    // PUT method to update an existing prescription
    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updatePrescription(@PathParam("prescriptionId") int prescriptionId, Prescription updatedPrescription) {
        Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);

        if (existingPrescription != null) {
            LOGGER.info("Updating prescription with ID : {}",prescriptionId);
            updatedPrescription.setId(prescriptionId);
            prescriptionDAO.updatePrescription(updatedPrescription);
            return "Prescription updated successfully";
        } else{
            // Throw an exception if prescription to be updated is not found
            throw new UserNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }
    }

    // DELETE method to delete a prescription by ID
    @DELETE
    @Path("/{prescriptionId}")
    public String deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
        if(prescription != null){
            LOGGER.info("Deleting prescription");
            prescriptionDAO.deletePrescription(prescriptionId);
            return "Prescription deleted successfully";
        }
        else{
            // Throw an exception if prescription to be deleted is not found
            throw new UserNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }
    }

    // GET method to retrieve prescriptions by doctor ID
    @GET
    @Path("/doctors/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getPrescriptionsByDoctor(@PathParam("doctorId") int doctorId) {
        LOGGER.info("Getting appointments by doctor ID : {}",doctorId);
        
        // Retrieve all prescriptions
        List<Prescription> appointments1 = prescriptionDAO.getAllPrescription();
        List<Prescription> doctorPrescriptions = new ArrayList<>();
        
        // Filter prescriptions by doctor ID
        for(Prescription prescription : appointments1){
            if((prescription.getDoctor()).getId()== doctorId){
                doctorPrescriptions.add(prescription);
            }
        }
        
        return doctorPrescriptions;
    }
    
    // GET method to retrieve prescriptions by patient ID
    @GET
    @Path("/patients/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getPrescriptionsByPatient(@PathParam("patientId") int patientId) {
        LOGGER.info("Getting appointments by patient ID : {}",patientId);
        
        // Retrieve all prescriptions
        List<Prescription> prescriptions1 = prescriptionDAO.getAllPrescription();
        List<Prescription> patientPrescriptions = new ArrayList<>();
        
        // Filter prescriptions by patient ID
        for(Prescription prescription : prescriptions1){
            if((prescription.getPatient()).getId()== patientId){
                patientPrescriptions.add(prescription);
            }
        }
        
        return patientPrescriptions;
    }
}

