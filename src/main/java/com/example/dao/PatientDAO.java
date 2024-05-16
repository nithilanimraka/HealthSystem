/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author Nithila
 */
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Data Access Object (DAO) class to interact with Patient objects
public class PatientDAO {
    
    // List to store Patient objects
    private static List<Patient> patients= new ArrayList<>();
    // Static block to initialize some sample patient objects
    static{
        
        patients.add(new Patient("diabetes","good",1,"Jason",0734244234,"44, Real avenue"));
        patients.add(new Patient("none","very good",2,"Kavin",327443223,"56, Estate avenue"));
    }
    
    // Method to retrieve all Patinent objects
    public List<Patient> getAllPatients(){
        return patients;
    }
    
    // Method to retrieve a Patient object by its ID
    public Patient getPatientById(int id){
        for(Patient patient: patients){
            if(patient.getId()==id){
                return patient;
            }
        }
        return null;// If no matching ID is found
    }
    
    // Method to add a new Patient object
    public void addPatient(Patient patient){
        int newUserId = getNextUserId();
        patient.setId(newUserId);
        patients.add(patient);
    }
    
    // Method to update an existing Patient object
    public void updatePatient(Patient updatedPatient){
        for(int i=0; i<patients.size();i++){
            Patient patient = patients.get(i);
            if(patient.getId()== updatedPatient.getId()){
                patients.set(i, updatedPatient);
                System.out.println("Patient updated: "+ updatedPatient);
                return;
                     
            }
        }
    }
    
    // Method to delete a Patient object by its ID
    public void deletePatient(int id){
        patients.removeIf(patient -> patient.getId() == id);
    }
    
    // Method to get the next available ID for a new Patient
    public int getNextUserId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Patient patient : patients) {
            int userId = patient.getId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}
