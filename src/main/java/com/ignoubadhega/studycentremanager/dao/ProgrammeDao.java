package com.ignoubadhega.studycentremanager.dao;

import com.ignoubadhega.studycentremanager.entity.Programme;

public interface ProgrammeDao {
    public Programme findProgrammeByName(String progName);
}
