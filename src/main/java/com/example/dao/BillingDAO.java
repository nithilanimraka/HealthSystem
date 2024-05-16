/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author Nithila
 */
import com.example.model.Billing;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Data Access Object (DAO) class to interact with Billing objects
public class BillingDAO {
    
    // List to store Billing objects
    private static List<Billing> billings= new ArrayList<>();
    
    // Static block to initialize some sample Billing objects
    static{
        
        Patient pat1= new Patient("diabetes","good",1,"Jason",0734244234,"44, Real avenue");
        Patient pat2 = new Patient("none","very good",2,"Kavin",327443223,"56, Estate avenue");
        
        billings.add(new Billing(pat1, 1,50.0,2.0));
        billings.add(new Billing(pat2, 2, 40.0,5.0));
    }
    
    // Method to retrieve all Billing objects
    public List<Billing> getAllBilling(){
        return billings;
    }
    
    // Method to retrieve a Billing object by its ID
    public Billing getBillingById(int id){
        for(Billing billing: billings){
            if(billing.getId()==id){
                return billing;
            }
        }
        return null;// If no matching ID is found
    }
    
    // Method to add a new Billing object
    public void addBilling(Billing billing){
        int newUserId = getNextUserId();
        billing.setId(newUserId);
        billings.add(billing);
    }
    
    // Method to update an existing Billing object
    public void updateBilling(Billing updatedBilling){
        for(int i=0; i<billings.size();i++){
            Billing billing = billings.get(i);
            if(billing.getId()== updatedBilling.getId()){
                billings.set(i, updatedBilling);
                System.out.println("Billing updated: "+ updatedBilling);
                return;
                     
            }
        }
    }
    
    // Method to delete a Billing object by its ID
    public void deleteBilling(int id){
        billings.removeIf(billing -> billing.getId() == id);
    }
    
    // Method to get the next available ID for a new Billing 
    public int getNextUserId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Billing billing : billings) {
            int userId = billing.getId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}
