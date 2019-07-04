package com.ignoubadhega.studycentremanager.service;

import java.util.List;

import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.utils.Pair;

public interface StudentService {

    public List<StudentDto> findStudentsWithEnrollNos(List<Long> enrollNos);

    public void save(List<StudentDto> students);

    public List<StudentDto> findStudentsWithEmails(List<String> emails);

    public List<StudentDto> findStudentsWithMobNos(List<Long> mobNos);

    public Pair<Long, StudentDto> findStudentByEnrollNo(Long enrollNo);

    public Pair<Long, StudentDto> findStudentByMobNo(Long mob);

    public Pair<Long, StudentDto> findStudentByEmail(String email);

    public void update(Pair<Long, StudentDto> student);

    public void delete(Long id);

}
