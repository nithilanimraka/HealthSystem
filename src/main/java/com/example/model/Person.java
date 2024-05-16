/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author Nithila
 */
public class Person {
    
    //Instance variables to store person's information
    int id;
    String name;
    long contactNo;
    String address;
    
    //Default constructor
    public Person(){
        
    }
    
    //Parameterized constructor to initialize Person's attributes 
    public Person(int id,String name, long contactNo, String address){
        this.id= id;
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
    }
    
    //Getters to get person's attributes 
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public long getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }
    
    //Setters to set the person's attributes
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
