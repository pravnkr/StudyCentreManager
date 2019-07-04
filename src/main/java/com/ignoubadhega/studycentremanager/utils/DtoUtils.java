package com.ignoubadhega.studycentremanager.utils;

import com.ignoubadhega.studycentremanager.dto.SemesterDto;
import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.dto.TeacherDto;
import com.ignoubadhega.studycentremanager.entity.Semester;
import com.ignoubadhega.studycentremanager.entity.Student;
import com.ignoubadhega.studycentremanager.entity.Teacher;

public class DtoUtils {

    public static StudentDto
            dtoOf(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto theStudent = new StudentDto();
        theStudent.setFirstname(student.getFirstname());
        theStudent.setLastname(student.getLastname());
        theStudent.setEnrollNo(student.getEnrollNo());
        theStudent.setEmail(student.getEmail());
        theStudent.setMob(student.getMob());
        theStudent
            .setProgramme(student
                .getSemester()
                .getProgramme()
                .getProgName()
                .toString());
        theStudent.setSemester(student.getSemester().getSemNo());
        return theStudent;
    }

    public static SemesterDto dtoOf(Semester sem) {
        if (sem == null) {
            return null;
        }
        SemesterDto semester = new SemesterDto();
        semester.setProgramme(sem.getProgramme().getProgName().toString());
        semester.setSemNo(sem.getSemNo());
        return semester;
    }
    
    public static TeacherDto dtoOf(Teacher teacher, String[] subjects) {
        if (teacher == null || subjects == null) {
            return null;
        }
        
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setFirstname(teacher.getFirstname());
        teacherDto.setLastname(teacher.getLastname());
        teacherDto.setMob(teacher.getMob());
        teacherDto.setSubjects(subjects);
        return teacherDto;
    }

    public static SemesterDto dtoToDtoFrom(StudentDto student) {
        if (student == null) {
            return null;
        }
        SemesterDto sem = new SemesterDto();
        sem.setProgramme(student.getProgramme());
        sem.setSemNo(student.getSemester());
        return sem;
    }

    public boolean studentDtoHas(StudentDto stud, SemesterDto sem) {
        if (stud == null || sem == null) {
            return false;
        }
        return stud.getProgramme().equals(sem.getProgramme())
                && stud.getSemester().equals(sem.getSemNo());
    }
}
