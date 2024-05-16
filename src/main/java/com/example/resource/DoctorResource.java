/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Nithila
 */
import com.example.model.Doctor;
import com.example.dao.DoctorDAO;
import com.example.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

// Resource class to handle HTTP requests related to Doctor objects
@Path("/doctors")
public class DoctorResource {

    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class);

    // Data Access Object for interacting with Doctor objects
    private DoctorDAO doctorDAO = new DoctorDAO();

    // GET method to retrieve all doctors
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        LOGGER.info("Getting all doctors");
        return doctorDAO.getAllDoctors();
    }

    // GET method to retrieve a doctor by ID
    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctorById(@PathParam("doctorId") int doctorId) {

        LOGGER.info("Getting doctor by ID: {}", doctorId);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);
        if (doctor != null) {
            return doctor;
        } else {
            // Throw an exception if doctor is not found
            throw new UserNotFoundException("Doctor with ID " + doctorId + " not found.");
        }
       

    }

    // POST method to add a new doctor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addDoctor(Doctor doctor) {
        LOGGER.info("Adding new doctor");
        doctorDAO.addDoctor(doctor);
        return "Doctor added successfully";
    }

    // PUT method to update an existing doctor
    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateDoctor(@PathParam("doctorId") int doctorId,Doctor updatedDoctor) {
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);

        if (existingDoctor != null) {
            LOGGER.info("Updating doctor with ID : {}", doctorId);
            updatedDoctor.setId(doctorId);
            doctorDAO.updateDoctor(updatedDoctor);
            return "Doctor updated successfully";
        }else {
            // Throw an exception if doctor to be updated is not found
            throw new UserNotFoundException("Doctor with ID " + doctorId + " not found.");
        }
    }

    // DELETE method to delete a doctor by ID 
    @DELETE
    @Path("/{doctorId}")
    public String deleteDoctor(@PathParam("doctorId") int doctorId) {
        Doctor doctor = doctorDAO.getDoctorById(doctorId);
        if(doctor != null){
            LOGGER.info("Deleting doctor");
            doctorDAO.deleteDoctor(doctorId);
            return "Doctor deleted successfully";
        }
        else{
            // Throw an exception if doctor to be deleted is not found
            throw new UserNotFoundException("Doctor with ID " + doctorId + " not found.");
        }
    }
}

