package com.ignoubadhega.studycentremanager.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ignoubadhega.studycentremanager.validation.FieldMatch;
import com.ignoubadhega.studycentremanager.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class StudyCentreUser {

    
    private String userName;

    
    private String password;
    
    
    private String matchingPassword;

    
    private String firstName;

    
    private String lastName;
    
    private String[] roles;

    private String email;

    public StudyCentreUser() {

    }

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
    
    
}
