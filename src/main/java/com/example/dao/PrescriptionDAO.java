/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author Nithila
 */
import com.example.model.Doctor;
import com.example.model.Patient;
import com.example.model.Prescription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Data Access Object (DAO) class to interact with Prescription objects
public class PrescriptionDAO {
    
    // List to store Prescription objects
    private static List<Prescription> prescriptions= new ArrayList<>();
    
    // Static block to initialize some sample Prescription objects
    static{
        
        Patient pat1 = new Patient("diabetes","good",1,"Jason",0734244234,"44, Real avenue");
        Patient pat2 = new Patient("none","very good",2,"Kavin",327443223,"56, Estate avenue");
        
        Doctor doc1 = new Doctor("ENT",07734134,1,"Perera",0701231231,"52, Castle Street");
        Doctor doc2 = new Doctor("General",4352043,2,"Kamal",872143784,"50, Queen Street");
        
        prescriptions.add(new Prescription(1,"panadols","two pills thrice a day","after meal","5 days",pat1,doc1));
        prescriptions.add(new Prescription(2,"piriton","one pill thrice a day","after meal","5 days",pat2, doc2));
    }
    
    // Method to retrieve all Prescription objects
    public List<Prescription> getAllPrescription(){
        return prescriptions;
    }
    
    // Method to retrieve a Prescription object by its ID
    public Prescription getPrescriptionById(int id){
        for(Prescription prescription: prescriptions){
            if(prescription.getId()==id){
                return prescription;
            }
        }
        return null;// If no matching ID is found
    }
    
    // Method to add a new Prescription object
    public void addPrescription(Prescription prescription){
        int newUserId = getNextUserId();
        prescription.setId(newUserId);
        prescriptions.add(prescription);
    }
    
    // Method to update an existing Prescription object
    public void updatePrescription(Prescription updatedPrescription){
        for(int i=0; i<prescriptions.size();i++){
            Prescription prescription = prescriptions.get(i);
            if(prescription.getId()== updatedPrescription.getId()){
                prescriptions.set(i, updatedPrescription);
                System.out.println("Prescription updated: "+ updatedPrescription);
                return;
                     
            }
        }
    }
    
    // Method to delete a Person object by its ID
    public void deletePrescription(int id){
        prescriptions.removeIf(prescription -> prescription.getId() == id);
    }
    
    // Method to get the next available ID for a new Prescription
    public int getNextUserId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Prescription prescription : prescriptions) {
            int userId = prescription.getId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}