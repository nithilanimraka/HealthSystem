/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author Nithila
 */
public class MedicalRecord {
    
    //Instance variables to store medical record information
    int id;
    Patient patient;
    String diagnoses;
    String treatments;
    String allergies;
    

    //Default constructor
    public MedicalRecord() {
        
    }

    //Parameterized constructor to initialize Medical Record attributes 
    public MedicalRecord(int id, Patient patient, String diagnoses, String treatments, String allergies) {
        this.id = id;
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.allergies = allergies;
    }

    
    //Getters to get medical record's attributes 
    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public String getTreatments() {
        return treatments;
    }

    public String getAllergies() {
        return allergies;
    }
    
    //Setters to set medical record's attributes
    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    
    
    
}
