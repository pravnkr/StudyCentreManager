package com.ignoubadhega.studycentremanager.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ignoubadhega.studycentremanager.dto.SemesterDto;
import com.ignoubadhega.studycentremanager.entity.ProgrammeName;
import com.ignoubadhega.studycentremanager.entity.Semester;

@Repository
public class SemesterDaoImpl implements SemesterDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Semester> findSemByProgAndSemNo(List<SemesterDto> semesters) {
        if (semesters == null || semesters.isEmpty()) {
            return Collections.emptyList();
        }
        String jpqlString = "from Semester sem where ";
        for (int i = 0; i < semesters.size(); i++) {
            jpqlString += "semNo = :semNo" + i + " AND sem.programme.progName = :progName" + i;
            if (i != semesters.size() - 1) {
                jpqlString += " OR ";
            }
        }
        TypedQuery<Semester> query = entityManager.createQuery(jpqlString, Semester.class);
        for (int i = 0; i < semesters.size(); i++) {
            query.setParameter("semNo" + i, semesters.get(i).getSemNo());
            query.setParameter("progName" + i, ProgrammeName.valueOf(semesters.get(i).getProgramme()));
        }
        List<Semester> sems = null;
        try {
            sems = query.getResultList();
        }
        catch(Exception e) {
            sems = Collections.emptyList();
        }
        return sems;
    }

}
