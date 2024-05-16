/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author Nithila
 */
import com.example.model.MedicalRecord;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;

// Data Access Object (DAO) class to interact with Medical Record objects
public class MedicalRecordDAO {
    // List to store Medical Record objects
    private static List<MedicalRecord> records = new ArrayList<>();

    // Static block to initialize some sample Medical record objects
    static {
        Patient pat1 = new Patient("diabetes","good",1,"Jason",0734244234,"44, Real avenue");
        Patient pat2 = new Patient("none","very good",2,"Kavin",327443223,"56, Estate avenue");

        records.add(new MedicalRecord(1, pat1,"diabetes","Insulin","none"));
        records.add(new MedicalRecord(2, pat2,"fever","panadols","Seafood"));
        
    }

    // Method to retrieve all Medical record objects
    public List<MedicalRecord> getAllMedicalRecords() {
        return records;
    }

    // Method to retrieve a Medical record object by its ID
    public MedicalRecord getMedicalRecordById(int id) {
        for (MedicalRecord record : records) {
            if (record.getId() == id) {
                return record;
            }
        }
        return null;// If no matching ID is found
    }

    // Method to add a new Person object
    public void addMedicalRecord(MedicalRecord record) {
        int newUserId = getNextUserId();
        record.setId(newUserId);
        records.add(record);
    }

    // Method to update an existing Person object
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < records.size(); i++) {
            MedicalRecord record = records.get(i);
            if (record.getId() == updatedMedicalRecord.getId()) {
                records.set(i, updatedMedicalRecord);
                System.out.println("Medical Record updated: "+ updatedMedicalRecord);
                return;
            }
        }
    }

    // Method to delete a Person object by its ID
    public void deleteMedicalRecord(int id) {
        records.removeIf(record -> record.getId() == id);
    }
    // Method to get the next available ID for a new Person
    public int getNextUserId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (MedicalRecord record : records) {
            int userId = record.getId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}

