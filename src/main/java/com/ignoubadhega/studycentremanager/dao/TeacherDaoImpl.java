package com.ignoubadhega.studycentremanager.dao;

import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ignoubadhega.studycentremanager.dto.TeacherDto;
import com.ignoubadhega.studycentremanager.entity.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    SubjectDao subDao;
    
    @Override
    public Teacher teacherExist(String email, String mob) {
        if (email == null || mob == null) {
            return null;
        }
        TypedQuery<Teacher> query = entityManager.createQuery("from Teacher WHERE email = :email OR mob = :mob", Teacher.class);
        query.setParameter("email", email);
        query.setParameter("mob", mob);
        Teacher teacher = null;
        try {
            teacher = query.getSingleResult();
        }
        catch (Exception e) {
            teacher = null;
        }
        return teacher;
    }

    @Override
    public void save(TeacherDto theTeacher) {
        Teacher teacher = new Teacher();
        teacher.setEmail(theTeacher.getEmail());
        teacher.setFirstname(theTeacher.getFirstname());
        teacher.setLastname(theTeacher.getLastname());
        teacher.setMob(theTeacher.getMob());
        teacher.setSubjects(new HashSet<>(Arrays.asList(subDao.findAllSubjectsWithCourseCodes(theTeacher.getSubjects()))));
        entityManager.merge(teacher);
    }

}
