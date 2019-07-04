package com.ignoubadhega.studycentremanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignoubadhega.studycentremanager.dao.BatchDao;
import com.ignoubadhega.studycentremanager.dao.SemesterDao;
import com.ignoubadhega.studycentremanager.dao.StudentDao;
import com.ignoubadhega.studycentremanager.dto.SemesterDto;
import com.ignoubadhega.studycentremanager.entity.Batch;
import com.ignoubadhega.studycentremanager.entity.Semester;
import com.ignoubadhega.studycentremanager.entity.Student;

@Service
public class BatchServiceImpl implements BatchService {

    private static final int batchMaxSize = 20;
    
    @Autowired
    BatchDao batchDao;
    
    @Autowired
    StudentDao studDao;
    
    @Autowired
    SemesterDao semDao;
    
    @Override
    @Transactional
    public List<String> generateBatchFor(SemesterDto semester) {
        if (semester == null || semester.getProgramme() == null) {
            return Collections.emptyList();
        }
        List<Student> semStudents = studDao.findStudentByProgDetails(semester.getProgramme(), semester.getSemNo());
        System.out.println("student list size: " + semStudents.size());
        Semester sem = semDao.findSemByProgAndSemNo(Arrays.asList(semester)).get(0);
        List<Batch> generatedBatches = generateBatchForStudents(semStudents, sem);
        System.out.println("batch list size: " + generatedBatches.size());
        batchDao.saveBatches(generatedBatches);
        return generatedBatches.stream().map(Batch::getBatchName).collect(Collectors.toList());
    }

    private static List<Batch> generateBatchForStudents(List<Student> semStudents, Semester sem) {
        List<Batch> batches = new ArrayList<>();
        for (int i = 0; i < semStudents.size(); i += batchMaxSize) {
            Batch batch = new Batch();
            batch.setBatchName("P-" + ((i / batchMaxSize) + 1));
            batch.setSemester(sem);
            Set<Student> students = new HashSet<>();
            for (int j = i; j < semStudents.size() && j < i + batchMaxSize; j++) {
                students.add(semStudents.get(j));
                semStudents.get(j).setBatch(batch);
            }
            System.out.println("students in batch size: " + students.size());
            batch.setStudents(students);
            batches.add(batch);
        }
        return batches;
    }

}
