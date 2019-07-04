package com.ignoubadhega.studycentremanager.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignoubadhega.studycentremanager.dao.SemesterDao;
import com.ignoubadhega.studycentremanager.dto.SemesterDto;
import com.ignoubadhega.studycentremanager.utils.DtoUtils;

@Service
public class SemesterServiceImpl implements SemesterService {

    private SemesterDao semDao;

    @Autowired
    public SemesterServiceImpl(SemesterDao dao) {
        this.semDao = dao;
    }

    @Override
    @Transactional
    public List<SemesterDto>
            findSemByProgAndSemNo(List<SemesterDto> semesters) {
        if (semesters == null || semesters.isEmpty()) {
            return Collections.emptyList();
        }
        return semDao
            .findSemByProgAndSemNo(semesters)
                .stream()
                .map(DtoUtils::dtoOf)
                .collect(Collectors.toList());
    }

}
