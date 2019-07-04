package com.ignoubadhega.studycentremanager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "programme")
public class Programme {

    private Long progId;
    private ProgrammeName progName;
    Set<Semester> progSems = new HashSet<>();

    public Programme() {
        super();
    }

    public Programme(ProgrammeName progName) {
        super();
        this.progName = progName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_programme_id")
    public Long getProgId() {
        return progId;
    }

    public void setProgId(Long progId) {
        this.progId = progId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "programme_name", nullable = false, unique = true)
    public ProgrammeName getProgName() {
        return progName;
    }

    public void setProgName(ProgrammeName progName) {
        this.progName = progName;
    }

    @OneToMany(mappedBy = "programme")
    public Set<Semester> getProgSems() {
        return progSems;
    }

    public void setProgSems(Set<Semester> progSems) {
        this.progSems = progSems;
    }

}
