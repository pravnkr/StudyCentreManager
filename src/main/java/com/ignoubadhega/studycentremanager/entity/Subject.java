package com.ignoubadhega.studycentremanager.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

    private Long subId;
    private String courseCode;
    private String courseTitle;
    private Short credits;
    private Short hours;
    private Set<Semester> semesters = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();

    public Subject() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_subject_id")
    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    @Column(name = "course_code", nullable = false, unique = true)
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Column(name = "course_title", nullable = false, unique = true)
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Column(name = "credits", nullable = false)
    public Short getCredits() {
        return credits;
    }

    public void setCredits(Short credits) {
        this.credits = credits;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "semester_subjects",
            joinColumns = { @JoinColumn(name = "db_subject_id") },
            inverseJoinColumns = { @JoinColumn(name = "db_semester_id") })
    public Set<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(Set<Semester> semesters) {
        this.semesters = semesters;
    }
    @Column(name = "total_hours", nullable = false)
    public Short getHours() {
        return hours;
    }

    public void setHours(Short hours) {
        this.hours = hours;
    }

    @ManyToMany(mappedBy = "subjects")
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courseCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subject other = (Subject) obj;
        return Objects.equals(courseCode, other.courseCode);
    }

}
