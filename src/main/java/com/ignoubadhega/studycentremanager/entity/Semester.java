package com.ignoubadhega.studycentremanager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "semester",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "semester_no", "db_programme_id" }
        )
)
public class Semester {

    private long semId;
    private Short semNo;
    private Programme programme;
    private Set<Subject> subjects = new HashSet<>();
    private Set<Student> students = new HashSet<>();

    public Semester() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_semester_id")
    public long getSemId() {
        return semId;
    }

    public void setSemId(long semId) {
        this.semId = semId;
    }

    @Column(name = "semester_no", nullable = false)
    public Short getSemNo() {
        return semNo;
    }

    public void setSemNo(Short semNo) {
        this.semNo = semNo;
    }

    @ManyToOne
    @JoinColumn(name = "db_programme_id")
    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "semesters"
    )
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "semester"
    )
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

}
