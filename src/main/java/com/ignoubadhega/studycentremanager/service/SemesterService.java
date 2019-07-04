package com.ignoubadhega.studycentremanager.service;

import java.util.List;

import com.ignoubadhega.studycentremanager.dto.SemesterDto;

public interface SemesterService {

    // return list of all sem dtos that are found in db
    public List<SemesterDto> findSemByProgAndSemNo(List<SemesterDto> semesters);
    
}
