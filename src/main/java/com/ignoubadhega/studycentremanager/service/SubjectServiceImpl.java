package com.ignoubadhega.studycentremanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignoubadhega.studycentremanager.dao.SubjectDao;
import com.ignoubadhega.studycentremanager.entity.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subDao;
    
    @Autowired
    public SubjectServiceImpl(SubjectDao subDao) {
        this.subDao = subDao;
    }

    @Override
    @Transactional
    public String[] findAllSubjectCodes() {
        return subDao.findAllSubjectCodes();
    }

    @Override
    @Transactional
    public Subject[] findAllSubjectsWithCourseCodes(String[] courseCodes) {
        if (courseCodes == null || courseCodes.length == 0) {
            return new Subject[0];
        }
        return subDao.findAllSubjectsWithCourseCodes(courseCodes);
    }

    
}
