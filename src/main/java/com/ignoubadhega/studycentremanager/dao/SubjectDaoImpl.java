package com.ignoubadhega.studycentremanager.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ignoubadhega.studycentremanager.entity.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public String[] findAllSubjectCodes() {
        TypedQuery<String> query = entityManager.createQuery("SELECT courseCode from Subject", String.class);
        List<String> result = null;
        try {
            result = query.getResultList();
        }
        catch (Exception e) {
            result = Collections.emptyList();
        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public Subject[] findAllSubjectsWithCourseCodes(String[] courseCodes) {
        if (courseCodes == null || courseCodes.length == 0) {
            return new Subject[0];
        }
        TypedQuery<Subject> query = entityManager.createQuery("FROM Subject where courseCode in :courseCodes", Subject.class);
        List<String> codes = Arrays.asList(courseCodes);
        query.setParameter("courseCodes", codes);
        Subject[] subjects = null;
        try {
            subjects = query.getResultStream().toArray(Subject[]::new);
        }
        catch (Exception e) {
            subjects = new Subject[0];
        }
        return subjects;
    }

}
