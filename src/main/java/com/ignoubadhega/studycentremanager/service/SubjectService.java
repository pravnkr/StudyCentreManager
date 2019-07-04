package com.ignoubadhega.studycentremanager.service;

import com.ignoubadhega.studycentremanager.entity.Subject;

public interface SubjectService {

    String[] findAllSubjectCodes();
    Subject[] findAllSubjectsWithCourseCodes(String[] courseCodes);
    
}
