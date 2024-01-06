/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.services;

import java.time.LocalDate;

/**
 *
 * @author Mostafa Mohamed
 */
public class Register {
    
    private String userFullName;
    private String userUniqueName;
    private LocalDate userDOB;
    private String userEmail;
    private String userPassword;
    private char gender;
    
    public Register(){
    
    }

   
    public Register(String userFullName, String userUniqueName, LocalDate userDOB, char gender, String userEmail, String userPassword) {
        this.userFullName = userFullName;
        this.userUniqueName = userUniqueName;
        this.userDOB = userDOB;
        this.gender = gender;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public char getUserGender() {
        return gender;
    }

    public void setUserGender(char gender) {
        this.gender = gender;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserUniqueName() {
        return userUniqueName;
    }

    public void setUserUniqueName(String userUniqueName) {
        this.userUniqueName = userUniqueName;
    }

    public LocalDate getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(LocalDate userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmai(String userEmai) {
        this.userEmail = userEmai;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    
}
