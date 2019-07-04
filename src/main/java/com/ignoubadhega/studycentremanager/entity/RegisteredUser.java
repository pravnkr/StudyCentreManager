package com.ignoubadhega.studycentremanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "registered_user")
public class RegisteredUser {

    private Long dbUserId;
    private String userName;
    private String passwHash;
    private String firstName;
    private String lastName;
    private String email;

    public RegisteredUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_user_id")
    public Long getDbUserId() {
        return dbUserId;
    }

    @Override
    public String toString() {
        return "RegisteredUser [dbUserId="
               + dbUserId
               + ", userName="
               + userName
               + ", passwHash="
               + passwHash
               + ", firstName="
               + firstName
               + ", lastName="
               + lastName
               + ", email="
               + email
               + "]";
    }

    public void setDbUserId(Long dbUserId) {
        this.dbUserId = dbUserId;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "passw_hash", nullable = false)
    public String getPasswHash() {
        return passwHash;
    }

    public void setPasswHash(String passwHash) {
        this.passwHash = passwHash;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
