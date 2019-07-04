package com.ignoubadhega.studycentremanager.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ignoubadhega.studycentremanager.entity.Programme;
import com.ignoubadhega.studycentremanager.entity.ProgrammeName;

public class ProgrammeDaoImpl implements ProgrammeDao {

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public Programme findProgrammeByName(String progName) {
        TypedQuery<Programme> query = entityManager.createQuery("from Programme where progName = :programName", Programme.class);
        query.setParameter("programName", ProgrammeName.valueOf(progName.toUpperCase()));
        Programme prog = null;
        try {
            prog = query.getSingleResult();
        }
        catch(Exception e) {
            prog = null;
        }
        return prog;
    }
}
