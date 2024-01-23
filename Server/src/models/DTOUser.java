
package models;

import java.time.LocalDate;
import java.sql.Date;

public class DTOUser {
    private String user_unique_name;
    private String email;
    private String name;
    private String password;
    private int balance;
    private Date dob;
    private String gender;
    private byte[] image;
    
    public DTOUser(){
        
    }

    public DTOUser(String user_unique_name) {
        this.user_unique_name = user_unique_name;
    }

    
    
    //Constructor with all attributes
    public DTOUser(String user_unique_name, String email, String name, String password, int balance, LocalDate dob, String gender) {
        this.user_unique_name = user_unique_name;
        this.email = email;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.dob = java.sql.Date.valueOf(dob);
        this.gender = gender;
    }
    
    //Constructor for retieve user data from DB to clien homescene
    public DTOUser(String user_unique_name, String email, String name, int balance, LocalDate dob, String gender){
        this.user_unique_name = user_unique_name;
        this.email = email;
        this.name = name;
        this.balance = balance;
        this.dob = java.sql.Date.valueOf(dob);
        this.gender = gender;
    }

    //Register Constructor for holding the registeration data
    public DTOUser(String user_unique_name, String email, String name, String password, LocalDate dob, String gender) {
        this.user_unique_name = user_unique_name;
        this.email = email;
        this.name = name;
        this.password = password;
        this.dob = java.sql.Date.valueOf(dob);
        this.gender = gender;
    }
    
    public DTOUser(String userUniqueName, String email, String name, int balance, LocalDate dob, String gender, byte[] photoBytes) {
        this.user_unique_name = userUniqueName;
        this.email = email;
        this.name = name;
        this.balance = balance;
        this.dob = java.sql.Date.valueOf(dob);
        this.gender = gender;
        this.image = photoBytes;
    }
    
    public DTOUser(String user_unique_name, String email, String name, int balance, Date dob, String gender, byte[] image) {
        this.user_unique_name = user_unique_name;
        this.email = email;
        this.name = name;
        this.balance = balance;
        this.dob = dob;
        this.gender = gender;
        this.image = image;
    }

    public DTOUser(String user_unique_name, int balance) {
        this.user_unique_name = user_unique_name;
        this.balance = balance;
    }

    public DTOUser(String user_unique_name, String email, Date dob) {
        this.user_unique_name = user_unique_name;
        this.email = email;
        this.dob = dob;
    }
    
    
    public String getUserUniqueName() {
        return this.user_unique_name;
    }

    public void setUser_name(String user_unique_name) {
        this.user_unique_name = user_unique_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public byte[] getImage() {
        return image;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public java.sql.Date getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = java.sql.Date.valueOf(dob);
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
