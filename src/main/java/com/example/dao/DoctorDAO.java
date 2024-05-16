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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Data Access Object (DAO) class to interact with Doctor objects
public class DoctorDAO {
    
    // List to store Doctor objects 
    private static List<Doctor> doctors= new ArrayList<>();
    // Static block to initialize some sample Doctor objects
    static{
        doctors.add(new Doctor("ENT",07734134,1,"Perera",0701231231,"52, Castle Street"));
        doctors.add(new Doctor("General",4352043,2,"Kamal",872143784,"50, Queen Street"));
    }
    
    
    // Method to retrieve all Doctor objects
    public List<Doctor> getAllDoctors(){
        return doctors;
    }
    
    // Method to retrieve a Doctor object by its ID
    public Doctor getDoctorById(int id){
        for(Doctor doctor: doctors){
            if(doctor.getId()==id){
                return doctor;
            }
        }
        return null;// If no matching ID is found
    }
    
    // Method to add a new Doctor object
    public void addDoctor(Doctor doctor){
        int newUserId = getNextUserId();
        doctor.setId(newUserId);
        doctors.add(doctor);
    }
    
    // Method to update an existing Doctor object
    public void updateDoctor(Doctor updatedDoctor){
        for(int i=0; i<doctors.size();i++){
            Doctor doctor = doctors.get(i);
            if(doctor.getId()== updatedDoctor.getId()){
                doctors.set(i, updatedDoctor);
                System.out.println("Doctor updated: "+ updatedDoctor);
                return;
                     
            }
        }
    }
    
    // Method to delete a Doctor object by its ID
    public void deleteDoctor(int id){
        doctors.removeIf(doctor -> doctor.getId() == id);
    }
    
    public int getNextUserId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Doctor doctor : doctors) {
            int userId = doctor.getId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}
    
