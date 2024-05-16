/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author Nithila
 */
public class Billing {
    
    //Instance variables to store Billinginformation
    Patient patient;
    int invoiceID;
    double price;
    double balance;

    //Default constructor
    public Billing() {
        
    }

    //Parameterized constructor to initialize Billing attributes 
    public Billing(Patient patient, int invoiceID, double price, double balance) {
        this.patient = patient;
        this.invoiceID = invoiceID;
        this.price = price;
        this.balance = balance;
    }

    //Getters to get Billing attributes 
    public Patient getPatient(){
        return patient;
    }
    
    public int getId() {
        return invoiceID;
    }

    public double getPrice() {
        return price;
    }

    public double getBalance() {
        return balance;
    }
    
    //Setters to set Billing attributes 
    public void setPatient(Patient patient){
        this.patient= patient;
    }

    public void setId(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
