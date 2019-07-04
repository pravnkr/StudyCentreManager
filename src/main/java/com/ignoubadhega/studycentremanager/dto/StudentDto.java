package com.ignoubadhega.studycentremanager.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StudentDto {

    private static final long ENROLL_MIN_VALUE = 100000000L;
    private static final long ENROLL_MAX_VALUE = 999999999L;
    
    private static final long MOB_MIN_VALUE = 1000000000L;
    private static final long MOB_MAX_VALUE = 9999999999L;
    
    private static final short SEM_MIN = 1;
    private static final short SEM_MAX = 6;
    
    private Long enrollNo;
    private String firstname;
    private String lastname;
    private String email;
    private Long mob;
    private String programme;
    private Short semester;

    @NotNull(message="Is required.", groups = {EnrollNoValidationGroup.class, DefaultValidationGroup.class})
    @Min(value = ENROLL_MIN_VALUE, message = "Invalid enrollment no.", groups = {EnrollNoValidationGroup.class, DefaultValidationGroup.class})
    @Max(value = ENROLL_MAX_VALUE, message = "Invalid enrollment no.", groups = {EnrollNoValidationGroup.class, DefaultValidationGroup.class})
    public Long getEnrollNo() {
        return enrollNo;
    }

    public void setEnrollNo(Long enrollNo) {
        this.enrollNo = enrollNo;
    }

    @NotBlank(message = "Is required.", groups = {DefaultValidationGroup.class})
    @Pattern(regexp = "^[A-Za-z]+$", message = "Must contain only characters.", groups = {DefaultValidationGroup.class})
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @NotBlank(message="Is required.", groups = {DefaultValidationGroup.class})
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
    public Long getMob() {
        return mob;
    }

    public void setMob(Long mob) {
        this.mob = mob;
    }

    @NotBlank(message = "is required", groups = {DefaultValidationGroup.class})
    @Pattern(regexp = "^[BMbm][Cc][Aa]$", groups = {DefaultValidationGroup.class})
    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    @NotNull
    @Min(value = SEM_MIN, message = "Invalid Semester", groups = {DefaultValidationGroup.class})
    @Max(value = SEM_MAX, message = "Invalid Semester", groups = {DefaultValidationGroup.class})
    public Short getSemester() {
        return semester;
    }

    public void setSemester(Short semester) {
        this.semester = semester;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result =
                prime * result
                        + ((enrollNo == null) ? 0 : enrollNo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentDto other = (StudentDto) obj;
        if (enrollNo == null) {
            if (other.enrollNo != null)
                return false;
        } else if (!enrollNo.equals(other.enrollNo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "StudentDto (enrollNo="
            + enrollNo
            + ", firstname="
            + firstname
            + ", lastname="
            + lastname
            + ", email="
            + email
            + ", mob="
            + mob
            + ", programme="
            + programme
            + ", semester="
            + semester
            + ")";
    }
    
    // marker interfaces for validation
    
    public interface EmailValidationGroup {}
    public interface EnrollNoValidationGroup {}
    public interface MobNoValidationGroup {}
    public interface DefaultValidationGroup {}
    
}
