package com.ignoubadhega.studycentremanager.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignoubadhega.studycentremanager.dao.StudentDao;
import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.entity.Student;
import com.ignoubadhega.studycentremanager.utils.DtoUtils;
import com.ignoubadhega.studycentremanager.utils.Pair;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public List<StudentDto> findStudentsWithEnrollNos(List<Long> enrollNos) {
        if (enrollNos == null || enrollNos.isEmpty()) {
            return Collections.emptyList();
        }
        List<com.ignoubadhega.studycentremanager.entity.Student> students =
                studentDao.findStudentsWithEnrollNos(enrollNos);
        return students
            .stream()
            .map(DtoUtils::dtoOf)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(List<StudentDto> students) {
        if (students == null || students.isEmpty()) {
            return;
        }
        studentDao.save(students);
    }

    @Override
    @Transactional
    public List<StudentDto> findStudentsWithEmails(List<String> emails) {
        if (emails == null || emails.isEmpty()) {
            return Collections.emptyList();
        }
        List<com.ignoubadhega.studycentremanager.entity.Student> students =
                studentDao.findStudentsWithEmails(emails);
        return students
            .stream()
            .map(DtoUtils::dtoOf)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<StudentDto> findStudentsWithMobNos(List<Long> mobNos) {
        if (mobNos == null || mobNos.isEmpty()) {
            return Collections.emptyList();
        }
        List<com.ignoubadhega.studycentremanager.entity.Student> students =
                studentDao.findStudentsWithMobNos(mobNos);
        return students
            .stream()
            .map(DtoUtils::dtoOf)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Pair<Long, StudentDto> findStudentByEnrollNo(Long enrollNo) {
        Student student = studentDao.findStudentByEnroll(enrollNo);
        if (student == null) {
            return null;
        }
        return new Pair<>(student.getStudentId(), DtoUtils.dtoOf(student));
    }

    @Override
    @Transactional
    public Pair<Long, StudentDto> findStudentByMobNo(Long mob) {
        Student student = studentDao.findStudentByMobNo(mob);
        if (student == null) {
            return null;
        }
        return new Pair<>(student.getStudentId(), DtoUtils.dtoOf(student));
    }

    @Override
    @Transactional
    public Pair<Long, StudentDto> findStudentByEmail(String email) {
        Student student = studentDao.findStudentByEmail(email);
        if (student == null) {
            return null;
        }
        return new Pair<>(student.getStudentId(), DtoUtils.dtoOf(student));
    }

    @Override
    @Transactional
    public void update(Pair<Long, StudentDto> student) {
        if (student == null || student.getId() == null || student.getValue() == null) {
            return;
        }
        studentDao.update(student);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        studentDao.delete(id);
    }
}
