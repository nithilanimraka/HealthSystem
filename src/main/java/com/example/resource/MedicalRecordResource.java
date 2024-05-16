/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Nithila
 */
import com.example.dao.MedicalRecordDAO;
import com.example.model.MedicalRecord;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.exception.UserNotFoundException;

// Resource class to handle HTTP requests related to MedicalRecord objects
@Path("/medicalRecords")
public class MedicalRecordResource {
    
    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordResource.class);
    // Data Access Object for interacting with MedicalRecord objects
    private MedicalRecordDAO recordDAO = new MedicalRecordDAO();

    // GET method to retrieve all medical records
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getAllRecord() {
        LOGGER.info("Getting all medical records");
        return recordDAO.getAllMedicalRecords();
    }

    // GET method to retrieve a medical record by ID
    @GET
    @Path("/{medicalRecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MedicalRecord getMedicalRecordById(@PathParam("medicalRecordId") int medicalRecordId) {
        LOGGER.info("Getting medical record by ID: {}", medicalRecordId);
        MedicalRecord record = recordDAO.getMedicalRecordById(medicalRecordId);
        if(record != null){
            return record;
        } else{
            // Throw an exception if appointment is not found 
            throw new UserNotFoundException("Medical Record with ID " + medicalRecordId + " not found.");
        }
        
    }

    // POST method to add a new medical record
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addMedicalRecord(MedicalRecord record) {
        LOGGER.info("Adding new medical record");
        recordDAO.addMedicalRecord(record);
        return "Medical record added successfully";
    }

    // PUT method to update an existing medical record
    @PUT
    @Path("/{medicalRecordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateMedicalRecord(@PathParam("medicalRecordId") int medicalRecordId, MedicalRecord updatedRecord) {
        MedicalRecord existingRecord = recordDAO.getMedicalRecordById(medicalRecordId);

        if (existingRecord != null) {
            LOGGER.info("Updating medical record with ID : {}",medicalRecordId);
            updatedRecord.setId(medicalRecordId);
            recordDAO.updateMedicalRecord(updatedRecord);
            return "Medical record updated successfully";
        } else{
            // Throw an exception if medical record to be updated is not found
            throw new UserNotFoundException("Medical Record with ID " + medicalRecordId + " not found.");
        }
    }

    // DELETE method to delete a medical record by ID
    @DELETE
    @Path("/{medicalRecordId}")
    public String deleteMedicalRecord(@PathParam("medicalRecordId") int medicalRecordId) {
        MedicalRecord medicalRecord = recordDAO.getMedicalRecordById(medicalRecordId);
        if(medicalRecord != null){
            LOGGER.info("Deleting medical record");
            recordDAO.deleteMedicalRecord(medicalRecordId);
            return "Medical record deleted successfully";
        }
        else{
            // Throw an exception if medical record to be deleted is not found
            throw new UserNotFoundException("Medical record with ID " + medicalRecordId + " not found.");
        }
    }

    // GET method to retrieve medical records by patient ID
    @GET
    @Path("/patients/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getMedicalRecordsByPatient(@PathParam("patientId") int patientId) {
        LOGGER.info("Getting appointments by patient ID : {}", patientId);
        
        // Retrieve all medical records
        List<MedicalRecord> medicalRecords1 = recordDAO.getAllMedicalRecords();
        List<MedicalRecord> patientMedicalRecords = new ArrayList<>();
        
        // Filter medical records by patient ID
        for(MedicalRecord medicalRecord : medicalRecords1){
            if((medicalRecord.getPatient()).getId()== patientId){
                patientMedicalRecords.add(medicalRecord);
            }
        }
        
        return patientMedicalRecords;
    }
}