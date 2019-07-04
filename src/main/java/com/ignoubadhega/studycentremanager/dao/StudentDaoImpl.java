package com.ignoubadhega.studycentremanager.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.entity.ProgrammeName;
import com.ignoubadhega.studycentremanager.entity.Semester;
import com.ignoubadhega.studycentremanager.entity.Student;
import com.ignoubadhega.studycentremanager.utils.Pair;

@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Student findStudentByEnroll(long enroll) {
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where enrollNo=:enrNo", Student.class);
        theQuery.setParameter("enrNo", enroll);
        Student theStudent = null;
        try {
            theStudent = theQuery.getSingleResult();
        } catch (Exception e) {
            theStudent = null;
        }

        return theStudent;
    }

    @Override
    public List<Student> findStudentsWithEnrollNos(List<Long> enrollNos) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where enrollNo IN :enrollNos", Student.class);
        query.setParameter("enrollNos", enrollNos);
        List<Student> result = null;
        try {
            result = query.getResultList();
        }
        catch (Exception e) {
            result = Collections.emptyList();
        }
        return result;
    }

    @Override
    public void save(List<StudentDto> students) {
        if (students == null || students.isEmpty()) {
            return;
        }
        for (StudentDto studDto : students) {
            TypedQuery<Semester> query = entityManager.createQuery("from Semester sem WHERE sem.semNo = :semNo AND sem.programme.progName = :progName", Semester.class);
            query.setParameter("semNo", studDto.getSemester());
            query.setParameter("progName", ProgrammeName.valueOf(studDto.getProgramme().toUpperCase()));
            Semester sem = null;
            try {
                sem = query.getSingleResult();
            }
            catch (Exception e) {
                sem = null;
            }
            if (sem != null) {
                Student stud = new Student();
                stud.setEnrollNo(studDto.getEnrollNo());
                stud.setEmail(studDto.getEmail());
                stud.setFirstname(studDto.getFirstname());
                stud.setLastname(studDto.getLastname());
                stud.setMob(studDto.getMob());
                stud.setSemester(sem);
                entityManager.merge(stud);
            }
        }
    }

    @Override
    public void update(List<Student> students) {
        for (Student student : students) {
            entityManager.merge(student);
        }
    }
    
    @Override
    public List<Student> findStudentsWithEmails(List<String> emails) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where email IN :emails", Student.class);
        query.setParameter("emails", emails);
        List<Student> result = null;
        try {
            result = query.getResultList();
        }
        catch (Exception e) {
            result = Collections.emptyList();
        }
        return result;
    }

    @Override
    public List<Student> findStudentsWithMobNos(List<Long> mobNos) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where mob IN :mobNos", Student.class);
        query.setParameter("mobNos", mobNos);
        List<Student> result = null;
        try {
            result = query.getResultList();
        }
        catch (Exception e) {
            result = Collections.emptyList();
        }
        return result;
    }

    @Override
    public Student findStudentByMobNo(Long mob) {
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where mob = :mobNo", Student.class);
        theQuery.setParameter("mobNo", mob);
        Student theStudent = null;
        try {
            theStudent = theQuery.getSingleResult();
        } catch (Exception e) {
            theStudent = null;
        }

        return theStudent;
    }

    @Override
    public Student findStudentByEmail(String email) {
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where email = :email", Student.class);
        theQuery.setParameter("email", email);
        Student theStudent = null;
        try {
            theStudent = theQuery.getSingleResult();
        } catch (Exception e) {
            theStudent = null;
        }

        return theStudent;
    }

    @Override
    public void update(Pair<Long, StudentDto> student) {
        if (student == null || student.getId() == null || student.getValue() == null) {
            return;
        }
        
        StudentDto studDto = student.getValue();
        
        TypedQuery<Semester> query = entityManager.createQuery("from Semester sem WHERE sem.semNo = :semNo AND sem.programme.progName = :progName", Semester.class);
        query.setParameter("semNo", studDto.getSemester());
        query.setParameter("progName", ProgrammeName.valueOf(studDto.getProgramme().toUpperCase()));
        Semester sem = null;
        try {
            sem = query.getSingleResult();
        }
        catch (Exception e) {
            sem = null;
        }
        if (sem != null) {
            Student stud = new Student();
            stud.setStudentId(student.getId());
            stud.setEnrollNo(studDto.getEnrollNo());
            stud.setEmail(studDto.getEmail());
            stud.setFirstname(studDto.getFirstname());
            stud.setLastname(studDto.getLastname());
            stud.setMob(studDto.getMob());
            stud.setSemester(sem);
            entityManager.merge(stud);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }

    @Override
    public List<Student>
            findStudentByProgDetails(String programme, short semNo) {
        TypedQuery<Student> query = entityManager.createQuery("From Student WHERE semester.semNo = :semNo AND semester.programme.progName = :progName ", Student.class);
        query.setParameter("semNo", semNo);
        query.setParameter("progName", ProgrammeName.valueOf(programme.toUpperCase()));
        List<Student> students = null;
        try {
            students = query.getResultList();
        }
        catch (Exception e) {
            students = Collections.emptyList();
        }
        return students;
    }

}
