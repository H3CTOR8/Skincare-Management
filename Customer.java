package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements IUser {
    private String customerSkinId;
    private static List<Customer> customerDatabase = new ArrayList<>(); //a list that act as an in-memory that representing customer

    // Constructors
    public Customer(String id, String name, String email, String password, String customerSkinId) {
        super(id, name, email, password); // Calls User's constructor for login information
        this.customerSkinId = customerSkinId;
    }

    public Customer(char gender, Timestamp dob, String address, String phoneNumber, String customerSkinId) {
        super(gender, dob, address, phoneNumber); // Calls User's constructor for signup information
        this.customerSkinId = customerSkinId;
    }

    //getter and setter for Skin Id
    public String getCustomerSkinId() {
        return customerSkinId;
    }

    public void setCustomerSkinId(String customerSkinId) {
        this.customerSkinId = customerSkinId;
    }

    //method for Signup 
    public static boolean signUp(String id, String name, String email, String password, String customerSkinId) throws DuplicateEmailException, IOException{ 
        for (Customer customer : customerDatabase) { //validation in customerDatabase
            if (customer.getEmail().equals(email)) { //check if there's any run-on email with the existing one
                throw new DuplicateEmailException("Sign Up failed: Email is already registered"); //exception in case there's duplicate email
            }
        }
        Customer newCustomer = new Customer(id, name, email, password, customerSkinId); //creating new customer
        customerDatabase.add(newCustomer);
        saveToFile(); //save to csv
        System.out.println("Sign up successful! Hello, " + name + "! Your role is: " + newCustomer.getRole());
        return true;
    }

    //method for Login
    public static Customer logIn(String email, String password) throws UserNotFoundException, IOException{  
        loadFromFile(); //load the save infos from csv
        //these are the validation to check if email and password are align with the signup account of customers or not
        for (Customer customer : customerDatabase) {
            if (customer.getEmail().equals(email)) {
                if (customer.password.equals(password)) {  
                    System.out.println("Login successful! Welcome, " + customer.getName() + "!");
                    return customer;
                } else {
                    System.out.println("Login failed! Invalid password.");
                    return null;
                }
            }
        }
        throw new UserNotFoundException("Login failed! Email not found: " + email);  //display the non founded email
    }

    //exception for login
    public static class DuplicateEmailException extends Exception {
        public DuplicateEmailException(String message) {
            super(message);
        }
    }

    //exception for signup
    public static class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    //File.I/O for saving the signup infos
    public static void saveToFile() throws IOException {
        FileWriter writer = new FileWriter("customer.csv");
        for (Customer customer : customerDatabase) {
            writer.write(customer.getId() + ", " + customer.getName() + ", " + customer.getEmail() + ", " + customer.password + ", " + customer.getCustomerSkinId() + "\n");
        }
        writer.close();
    }

    //File.I/O for loading the existing infos
    public static void loadFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("customer.csv")); //function for reading the any existing inofs
        String line;
        while ((line = reader.readLine()) != null) { //this was used to read all of the lines in the csv file
            String[] data = line.split(",");
            Customer customer = new Customer(data[0], data[1], data[2], data[3], data[4]); //data that was used to signup
            customerDatabase.add(customer);
        }
        reader.close();
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
        System.out.println("Skin ID: " + customerSkinId);
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}
