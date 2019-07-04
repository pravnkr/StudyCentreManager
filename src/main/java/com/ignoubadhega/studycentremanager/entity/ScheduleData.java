package com.ignoubadhega.studycentremanager.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule_data")
public class ScheduleData {

    private Long scheduleDataId;
    private Schedule schedule;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private Teacher teacher;
    private Subject subject;

    public ScheduleData() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_schedule_data_id")
    public Long getScheduleDataId() {
        return scheduleDataId;
    }

    public void setScheduleDataId(Long scheduleDataId) {
        this.scheduleDataId = scheduleDataId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "db_schedule_id")
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "from_time", nullable = false)
    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    @Column(name = "to_time", nullable = false)
    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    @ManyToOne
    @JoinColumn(name = "db_teacher_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne
    @JoinColumn(name = "db_subject_id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
