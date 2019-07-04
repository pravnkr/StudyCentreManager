package com.ignoubadhega.studycentremanager.service;

import com.ignoubadhega.studycentremanager.dto.TeacherDto;

public interface TeacherService {

    TeacherDto teacherExist(TeacherDto theTeacher);

    void save(TeacherDto theTeacher);

}
