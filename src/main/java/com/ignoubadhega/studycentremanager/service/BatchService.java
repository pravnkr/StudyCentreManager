package com.ignoubadhega.studycentremanager.service;

import java.util.List;

import com.ignoubadhega.studycentremanager.dto.SemesterDto;

public interface BatchService {

    List<String> generateBatchFor(SemesterDto semester);

}
