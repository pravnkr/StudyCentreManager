package com.ignoubadhega.studycentremanager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {

    private Long scheduleId;
    private Batch batch;
    private Set<ScheduleData> data = new HashSet<>();

    Schedule() {
    }

    public Schedule(Batch batch) {
        super();
        this.batch = batch;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_schedule_id")
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "db_batch_id")
    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    public Set<ScheduleData> getData() {
        return data;
    }

    public void setData(Set<ScheduleData> data) {
        this.data = data;
    }

}
