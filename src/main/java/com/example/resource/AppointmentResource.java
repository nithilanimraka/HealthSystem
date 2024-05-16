/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Nithila
 */
import com.example.dao.AppointmentDAO;
import com.example.model.Appointment;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.exception.UserNotFoundException;

// Resource class to handle HTTP requests related to Appointment objects
@Path("/appointments")
public class AppointmentResource {
    
    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class);
    
    // Data Access Object for interacting with Appointment objects
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    // GET method to retrieve all appointments
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointment() {
        LOGGER.info("Getting all appointments");
        return appointmentDAO.getAllAppointments();
    }

    // GET method to retrieve an appointment by ID
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        LOGGER.info("Getting appointment by ID: {}", appointmentId);
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
        if(appointment != null){
            return appointment;
        } else{
            // Throw an exception if appointment is not found 
            throw new UserNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }

    // POST method to add a new appointment
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addAppointment(Appointment appointment) {
        LOGGER.info("Adding new appointment");
        appointmentDAO.addAppointment(appointment);
        return "Appointment added successfully";
    }

    // PUT method to update an existing appointment
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);

        if (existingAppointment != null) {
            LOGGER.info("Updating appointment with ID : {}",appointmentId);
            updatedAppointment.setId(appointmentId);
            appointmentDAO.updateAppointment(updatedAppointment);
            return "Appointment updated successfully";
        } else{
            // Throw an exception if appointment to be updated is not found
            throw new UserNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }

    // DELETE method to delete an appointment by ID
    @DELETE
    @Path("/{appointmentId}")
    public String deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
        if(appointment != null){
            LOGGER.info("Deleting appointment");
            appointmentDAO.deleteAppointment(appointmentId);
            return "Appointment deleted successfully";
        }
        else{
            // Throw an exception if appointment to be deleted is not found
            throw new UserNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }

    // GET method to retrieve appointments by doctor ID
    @GET
    @Path("/doctors/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAppointmentsByDoctor(@PathParam("doctorId") int doctorId) {
        LOGGER.info("Getting appointments by doctor ID : {}", doctorId);
        
        // Retrieve all appointments
        List<Appointment> appointments1 = appointmentDAO.getAllAppointments();
        List<Appointment> doctorAppointments = new ArrayList<>();
        
        // Filter appointments by doctor ID
        for(Appointment appointment : appointments1){
            if((appointment.getDoctor()).getId()== doctorId){
                doctorAppointments.add(appointment);
            }
        }
        
        return doctorAppointments;
    }
    
    // GET method to retrieve appointments by patient ID
    @GET
    @Path("/patients/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAppointmentsByPatient(@PathParam("patientId") int patientId) {
        LOGGER.info("Getting appointments by patient ID : {}", patientId);
        
        // Retrieve all appointments
        List<Appointment> appointments2 = appointmentDAO.getAllAppointments();
        List<Appointment> patientAppointments = new ArrayList<>();
        
        // Filter appointments by patient ID
        for(Appointment appointment : appointments2){
            if((appointment.getPatient()).getId()== patientId){
                patientAppointments.add(appointment);
            }
        }
        
        return patientAppointments;
    }
}
