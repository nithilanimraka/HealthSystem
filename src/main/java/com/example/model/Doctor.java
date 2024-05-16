/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author Nithila
 */


public class Doctor extends Person{
    // Additional instance variables specific to Doctor class
    String specialization;
    long contactDoc;

    // Default constructor
    public Doctor(){
        
    }

    // Parameterized constructor to initialize instance variables including those from the superclass
    public Doctor(String specialization, int contactDoc,int id, String name, int contactNo, String address) {
        // Call to superclass constructor to initialize inherited instance variables
        super(id,name, contactNo, address);
        // Initialize instance variables specific to Patient class
        this.specialization = specialization;
        this.contactDoc = contactDoc;
    }
    
    
    //Getters to get the doctor attributes
    public String getSpecialization() {
        return specialization;
    }

    public long getContactDoc() {
        return contactDoc;
    }

    //Setters to set the doctor attributes
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setContactDoc(int contactDoc) {
        this.contactDoc = contactDoc;
    }
    
    
    
    
}
