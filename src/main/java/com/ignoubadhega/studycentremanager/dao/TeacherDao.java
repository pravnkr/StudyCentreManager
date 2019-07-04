package com.ignoubadhega.studycentremanager.dao;

import com.ignoubadhega.studycentremanager.dto.TeacherDto;
import com.ignoubadhega.studycentremanager.entity.Teacher;

public interface TeacherDao {

    Teacher teacherExist(String email, String mob);

    void save(TeacherDto theTeacher);

}
