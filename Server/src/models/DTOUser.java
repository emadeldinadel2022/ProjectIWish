
package models;

import java.time.LocalDate;
import java.sql.Date;

public class DTOUser {
    private String userUniqueName;
    private String email;
    private String name;
    private String password;
    private int balance;
    private Date dob;
    private String gender;
    
    public DTOUser(String userUniqueName, String email, String name, String password, int balance, LocalDate dob, String gender) {
        this.userUniqueName = userUniqueName;
        this.email = email;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.dob = java.sql.Date.valueOf(dob);
        this.gender = gender;
    }
    
    public DTOUser(String userUniqueName, String email, String name, int balance, LocalDate dob, String gender){
        this.userUniqueName = userUniqueName;
        this.email = email;
        this.name = name;
        this.balance = balance;
        this.dob = java.sql.Date.valueOf(dob);
        this.gender = gender;
    }

    public String getUserUniqueName() {
        return this.userUniqueName;
    }

    public void setUser_name(String userUniqueName) {
        this.userUniqueName = userUniqueName;
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
