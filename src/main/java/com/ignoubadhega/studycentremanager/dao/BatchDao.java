package com.ignoubadhega.studycentremanager.dao;

import java.util.List;

import com.ignoubadhega.studycentremanager.entity.Batch;

public interface BatchDao {

    void saveBatches(List<Batch> generatedBatches);


}
