package com.ignoubadhega.studycentremanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignoubadhega.studycentremanager.dao.TeacherDao;
import com.ignoubadhega.studycentremanager.dto.TeacherDto;
import com.ignoubadhega.studycentremanager.entity.Subject;
import com.ignoubadhega.studycentremanager.entity.Teacher;
import com.ignoubadhega.studycentremanager.utils.DtoUtils;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    @Transactional
    public TeacherDto teacherExist(TeacherDto theTeacher) {
        if (theTeacher == null || theTeacher.getMob() == null
                || theTeacher.getEmail() == null) {
            return null;
        }
        Teacher teacher =
                teacherDao
                    .teacherExist(theTeacher.getEmail(), theTeacher.getMob());
        if (teacher == null || teacher.getSubjects() == null) {
            return null;
        }
        return DtoUtils
            .dtoOf(teacher,
                    teacher
                        .getSubjects()
                        .stream()
                        .map(Subject::getCourseCode)
                        .toArray(String[]::new));
    }

    @Override
    @Transactional
    public void save(TeacherDto theTeacher) {
        if (theTeacher == null || theTeacher.getMob() == null
                || theTeacher.getEmail() == null) {
            return;
        }
        teacherDao.save(theTeacher);
    }

}
