/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Nithila
 */

import com.example.model.Person;
import com.example.dao.PersonDAO;
import com.example.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


// Resource class to handle HTTP requests related to Person objects
@Path("/persons")
public class PersonResource {
    
    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    // Data Access Object for interacting with Person objects
    private PersonDAO personDAO = new PersonDAO();

    // GET method to retrieve all persons
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        LOGGER.info("Getting all persons");
        return personDAO.getAllPersons();
    }

    // GET method to retrieve a person by ID
    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("personId") int personId) {

        LOGGER.info("Getting person by ID: {}", personId);
        Person person = personDAO.getPersonById(personId);
        if (person != null) {
            return person;
        } else {
            // Throw an exception if person is not found
            throw new UserNotFoundException("Person with ID " + personId + " not found.");
        }
       

    }

    // POST method to add a new person
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPerson(Person person) {
        LOGGER.info("Adding new Person");
        personDAO.addPerson(person);
        return "Person added successfully";
    }

    // PUT method to update an existing person
    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updatePerson(@PathParam("personId") int personId, Person updatedPerson) {
        Person existingPerson = personDAO.getPersonById(personId);

        if (existingPerson != null) {
            LOGGER.info("Updating Person with ID : {}", personId);
            updatedPerson.setId(personId);
            personDAO.updatePerson(updatedPerson);
            return "Person updated successfully";
        } else {
            // Throw an exception if person to be updated is not found
            throw new UserNotFoundException("Person with ID " + personId + " not found.");
        }
    }

    // DELETE method to delete a person by ID
    @DELETE
    @Path("/{personId}")
    public String deletePerson(@PathParam("personId") int personId) {
        Person person = personDAO.getPersonById(personId);
        if(person != null){
            LOGGER.info("Deleting person");
            personDAO.deletePerson(personId);
            return "Person deleted successfully";
        }
        else{
            // Throw an exception if person to be deleted is not found
            throw new UserNotFoundException("Person with ID " + personId + " not found.");
        }
    }
}
