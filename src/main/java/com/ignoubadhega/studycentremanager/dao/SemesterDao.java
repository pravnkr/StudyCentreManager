package com.ignoubadhega.studycentremanager.dao;

import java.util.List;

import com.ignoubadhega.studycentremanager.dto.SemesterDto;
import com.ignoubadhega.studycentremanager.entity.Semester;

public interface SemesterDao {
    public List<Semester> findSemByProgAndSemNo(List<SemesterDto> semesters);
}
