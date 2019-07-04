package com.ignoubadhega.studycentremanager.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import com.ignoubadhega.studycentremanager.config.TestConfig;
import com.ignoubadhega.studycentremanager.entity.Student;

@SpringJUnitConfig({TestConfig.class})
@Transactional
class GenericDaoImplTest {

//    @PersistenceContext
//    private EntityManager entityManager;
//    
//    @Autowired
//    private GenericDao<Student> repository;
//    
//    @Test
//    void test() {
//        Student student = new Student();
//        student.setEmail("kumarprince540@gmail.com");
//        student.setEnrollNo(168119058L);
//        student.setFirstname("praveen");
//        student.setLastname("kumar");
//        student.setMob(9868605104L);
//        
//        repository.save(student);
//        Student stud = entityManager.find(Student.class, 1L);
//        
//        assertEquals("praveen", stud.getFirstname());
//    }

}
