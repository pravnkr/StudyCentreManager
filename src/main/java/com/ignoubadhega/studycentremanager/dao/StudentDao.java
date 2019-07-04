package com.ignoubadhega.studycentremanager.dao;

import java.util.List;

import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.entity.Student;
import com.ignoubadhega.studycentremanager.utils.Pair;

public interface StudentDao {

    Student findStudentByEnroll(long enroll);

    void save(List<StudentDto> students);
    void update(List<Student> students);

    List<Student> findStudentsWithEnrollNos(List<Long> enrollNos);

    List<Student> findStudentsWithEmails(List<String> emails);

    List<Student> findStudentsWithMobNos(List<Long> mobNos);

    Student findStudentByMobNo(Long mob);

    Student findStudentByEmail(String email);

    void update(Pair<Long, StudentDto> student);

    void delete(Long id);

    List<Student> findStudentByProgDetails(String programme, short semNo);

}
