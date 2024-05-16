/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author Nithila
 */


public class Patient extends Person {
    // Additional instance variables specific to Patient class
    String medicalHistory;
    String healthStatus;

    // Default constructor
    public Patient() {
        
    }

    // Parameterized constructor to initialize instance variables including those from the superclass
    public Patient(String medicalHistory, String healthStatus, int id,String name, int contactNo, String address) {
        // Call to superclass constructor to initialize inherited instance variables
        super(id,name, contactNo, address);
        
        // Initialize instance variables specific to Patient class
        this.medicalHistory = medicalHistory;
        this.healthStatus = healthStatus;
    }

    //Getters to get patient attributes 
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    //Setters to set the patient attributes
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
    
    
}
