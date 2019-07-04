package com.ignoubadhega.studycentremanager.dao;

import com.ignoubadhega.studycentremanager.entity.Subject;

public interface SubjectDao {

    String[] findAllSubjectCodes();

    Subject[] findAllSubjectsWithCourseCodes(String[] courseCodes);

}
