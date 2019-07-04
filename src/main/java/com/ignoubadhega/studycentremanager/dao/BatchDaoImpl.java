package com.ignoubadhega.studycentremanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ignoubadhega.studycentremanager.entity.Batch;

@Repository
public class BatchDaoImpl implements BatchDao {

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public void saveBatches(List<Batch> generatedBatches) {
        for (Batch batch : generatedBatches) {
            entityManager.merge(batch);
        }
    }

}
