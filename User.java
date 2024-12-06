package Database;

import java.sql.Timestamp;

public abstract class User {

    //the reason of using protected is because I want
    //each classes to be confidential yet I also want it
    //to extends to other file such as(employee, costumer...)
    
    protected String id;
    protected String name;
    protected char gender;
    protected Timestamp dob;
    protected String email;
    protected String password;
    protected String address;
    protected String phoneNumber;

    //constructor

    //the first constructor is to asked about the login

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //the second one the options for signup or fill in infos
    public User(char gender, Timestamp dob, String address, String phoneNumber) {
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void displayAccountInfo() {
        System.out.println("User Account Info:");
        System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
    }

    // Abstract method for subclasses to define specific roles
    public abstract String getRole();

    //Getters and setters for the login

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
