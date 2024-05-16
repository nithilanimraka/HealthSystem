/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author Nithila
 */

import com.example.model.Appointment;
import com.example.model.Patient;
import com.example.model.Doctor;
import java.util.ArrayList;
import java.util.List;

// Data Access Object (DAO) class to interact with Appointment objects
public class AppointmentDAO {
    // List to store Appointment objects 
    private static List<Appointment> appointments = new ArrayList<>();

    // Static block to initialize some sample Appointment objects
    static {
        Patient pat1 = new Patient("diabetes","good",1,"Jason",0734244234,"44, Real avenue");
        Patient pat2 = new Patient("none","very good",2,"Kavin",327443223,"56, Estate avenue");
        
        Doctor doc1 = new Doctor("ENT",07734134,1,"Perera",0701231231,"52, Castle Street");
        Doctor doc2 = new Doctor("General",4352043,2,"Kamal",872143784,"50, Queen Street");

        appointments.add(new Appointment(1, "2024-01-02", "8.00am",pat1,doc1));
        appointments.add(new Appointment(2, "2024-02-05","10.00am",pat2,doc2));
    }

    // Method to retrieve all Appointment objects
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // Method to retrieve a Appointment object by its ID
    public Appointment getAppointmentById(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null;// If no matching ID is found
    }

    // Method to add a new Appointment object
    public void addAppointment(Appointment appointment) {
        int newUserId = getNextUserId();
        appointment.setId(newUserId);
        appointments.add(appointment);
    }

    // Method to update an existing Appointment object
    public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getId() == updatedAppointment.getId()) {
                appointments.set(i, updatedAppointment);
                System.out.println("Appointment updated: "+ updatedAppointment);
                return;
            }
        }
    }

    // Method to delete a Appointment object by its ID
    public void deleteAppointment(int id) {
        appointments.removeIf(appointment -> appointment.getId() == id);
    }
    
    public int getNextUserId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Appointment appointment : appointments) {
            int userId = appointment.getId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}
