package com.ignoubadhega.studycentremanager.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {

    private Long teacherId;
    private String firstname;
    private String lastname;
    private String email;
    private String mob;
    private Set<ScheduleData> teacherScheduleData = new HashSet<>();
    private Set<Subject> subjects = new HashSet<>();

    public Teacher() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_teacher_id")
    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "firstname", nullable = false)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname", nullable = false)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "mob", nullable = false, unique = true)
    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    @OneToMany(mappedBy = "teacher")
    public Set<ScheduleData> getTeacherScheduleData() {
        return teacherScheduleData;
    }

    public void setTeacherScheduleData(Set<ScheduleData> teacherScheduleData) {
        this.teacherScheduleData = teacherScheduleData;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "db_teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "db_subject_id")
    )
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        return Objects.equals(email, other.email);
    }
}
