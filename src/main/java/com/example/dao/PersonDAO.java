/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author Nithila
 */

import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;

// Data Access Object (DAO) class to interact with Person objects
public class PersonDAO {
    
    // List to store Person objects
    private static List<Person> persons = new ArrayList<>();
    
    // Static block to initialize some sample Person objects
    static{
        persons.add(new Person(1,"Nimal",0734244234,"44, Ella avenue"));
        persons.add(new Person(2,"Kamal",327443223,"56, Real avenue"));
    }


    // Method to retrieve all Person objects
    public List<Person> getAllPersons() {
        return persons;
    }
    
    // Method to retrieve a Person object by its ID
    public Person getPersonById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;// If no matching ID is found
    }

    // Method to add a new Person object
    public void addPerson(Person person) {
        int newUserId = getNextUserId();
        person.setId(newUserId);
        persons.add(person);
    }

    // Method to update an existing Person object
    public void updatePerson(Person updatedPerson) {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getId() == updatedPerson.getId()) {
                // Replace the existing Person with the updated one
                persons.set(i, updatedPerson);
                System.out.println("Person updated: " + updatedPerson);
                return;
            }
        }
    }

    // Method to delete a Person object by its ID
    public void deletePerson(int id) {
        persons.removeIf(person -> person.getId() == id);
    }
    
    // Method to get the next available ID for a new Person
    public int getNextUserId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Person person : persons) {
            int userId = person.getId();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}
