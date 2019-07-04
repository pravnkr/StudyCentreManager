package com.ignoubadhega.studycentremanager.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ignoubadhega.studycentremanager.dto.StudentDto.DefaultValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.EmailValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.MobNoValidationGroup;

public class TeacherDto {

    private static final long MOB_MIN_VALUE = 1000000000L;
    private static final long MOB_MAX_VALUE = 9999999999L;
    
    private String firstname;
    private String lastname;
    private String email;
    private String mob;
    private String[] subjects;

    @NotBlank(message = "Is required.", groups = {DefaultValidationGroup.class})
    @Pattern(regexp = "^[A-Za-z]+$", message = "Must contain only characters.", groups = {DefaultValidationGroup.class})
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @NotBlank(message = "Is required.", groups = {DefaultValidationGroup.class})
    @Pattern(regexp = "^[A-Za-z]+$", message = "Must contain only characters.", groups = {DefaultValidationGroup.class})
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @NotBlank(message="Is required.", groups = {EmailValidationGroup.class, DefaultValidationGroup.class})
    @Email(message = "Invalid email id.", groups = {EmailValidationGroup.class, DefaultValidationGroup.class})
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message="Is required", groups = {MobNoValidationGroup.class, DefaultValidationGroup.class})
    @Min(value = MOB_MIN_VALUE, message="invalid mobile no.", groups = {MobNoValidationGroup.class, DefaultValidationGroup.class})
    @Max(value = MOB_MAX_VALUE, message="invalid mobile no.", groups = {MobNoValidationGroup.class, DefaultValidationGroup.class})
    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

}
