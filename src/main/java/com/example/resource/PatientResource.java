
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Nithila
 */
import com.example.model.Patient;
import com.example.dao.PatientDAO;
import com.example.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

// Resource class to handle HTTP requests related to Patient objects
@Path("/patients")
public class PatientResource {

    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);

    // Data Access Object for interacting with Patient objects
    private PatientDAO patientDAO = new PatientDAO();

    // GET method to retrieve all patients
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients() {
        LOGGER.info("Getting all patients");
        return patientDAO.getAllPatients();
    }

    // GET method to retrieve a patient by ID
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("patientId") int patientId) {

        LOGGER.info("Getting patient by ID: {}", patientId);
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient != null) {
            return patient;
        } else {
            // Throw an exception if patient is not found
            throw new UserNotFoundException("Patient with ID " + patientId + " not found.");
        }
       

    }

    // POST method to add a new patient
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPatient(Patient patient) {
        LOGGER.info("Adding new patient");
        patientDAO.addPatient(patient);
        return "Patient added successfully";
    }

    // PUT method to update an existing patient
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updatePatient(@PathParam("patientId") int patientId, Patient updatedPatient) {
        Patient existingPatient = patientDAO.getPatientById(patientId);

        if (existingPatient != null) {
            LOGGER.info("Updating patient with ID : {}", patientId);
            updatedPatient.setId(patientId);
            patientDAO.updatePatient(updatedPatient);
            return "Patient updated successfully";
        } else {
            // Throw an exception if patient to be updated is not found
            throw new UserNotFoundException("Patient with ID " + patientId + " not found.");
        }
    }

    // DELETE method to delete a patient by ID
    @DELETE
    @Path("/{patientId}")
    public String deletePatient(@PathParam("patientId") int patientId) {
        Patient patient = patientDAO.getPatientById(patientId);
        if(patient != null){
            LOGGER.info("Deleting patient");
            patientDAO.deletePatient(patientId);
            return "Patient deleted successfully";
        }
        else{
            // Throw an exception if patient to be deleted is not found
            throw new UserNotFoundException("Patient with ID " + patientId + " not found.");
        }
    }
}

