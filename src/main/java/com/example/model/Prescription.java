/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author Nithila
 */
public class Prescription {
    
    //Instance variables to store Prescription information
    int id;
    Patient patient;
    Doctor doctor;
    String medication;
    String dosage;
    String instructions;
    String duration;

    //Default constructor
    public Prescription() {
    }

    //Parameterized constructor to initialize Prescription attributes 
    public Prescription(int id, String medication, String dosage, String instructions, String duration, Patient patient, Doctor doctor) {
        this.id = id;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
        this.patient = patient;
        this.doctor = doctor;
    }

    //Getters to get Prescription attributes
    public int getId() {
        return id;
    }
    
    public String getMedication() {
        return medication;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getDuration() {
        return duration;
    }
    
    public Patient getPatient(){
        return patient;
    }
    
    public Doctor getDoctor(){
        return doctor;
    }

    //Setters to set Prescription attributes
    public void setId(int id) {
        this.id = id;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    
    
    
    
    
}
