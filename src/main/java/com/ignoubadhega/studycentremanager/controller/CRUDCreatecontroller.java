package com.ignoubadhega.studycentremanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ignoubadhega.studycentremanager.dto.SemesterDto;
import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.dto.StudentDto.DefaultValidationGroup;
import com.ignoubadhega.studycentremanager.service.SemesterService;
import com.ignoubadhega.studycentremanager.service.StudentService;
import com.ignoubadhega.studycentremanager.utils.DtoUtils;

@Controller
public class CRUDCreatecontroller {
    public StudentService studentService;
    public SemesterService semService;
    public Logger logger = Logger.getLogger(getClass().getName());
    
    @Autowired
    public CRUDCreatecontroller(
            StudentService studentService,
            SemesterService semService
    ) {
        this.studentService = studentService;
        this.semService = semService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor =
                new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    
    @GetMapping("/coordinator/create-student")
    public String showCreateStudentView(Model model) {
        populateInfoInModelForFreshSavePage(model);
        return "create-student";
    }

    private void populateInfoInModelForFreshSavePage(Model model) {
        List<StudentDto> newStudents = new ArrayList<>();
        StudentDto theStudent = new StudentDto();
        newStudents.add(theStudent);
        ListBeanWrapper listWrapper = new ListBeanWrapper();
        listWrapper.setList(newStudents);
        model.addAttribute("studentsWrapper", listWrapper);
    }

    @PostMapping(
            params = "add-row",
            value = "/coordinator/create-student/save"
    )
    public String addRow(
            @ModelAttribute("studentsWrapper") ListBeanWrapper studentsWrapper,
            Model model
    ) {
        System.out.println("List Size: " + studentsWrapper.getList().size());
        studentsWrapper.getList().add(new StudentDto());
        model.addAttribute("studentsWrapper", studentsWrapper);
        return "create-student";
    }

    @PostMapping(params = "save", value = "/coordinator/create-student/save")
    public String saveCreatedStudent(
            @ModelAttribute("studentsWrapper") @Validated(
                DefaultValidationGroup.class
            ) ListBeanWrapper studentsWrapper,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("studentsWrapper", studentsWrapper);
            System.out.println("Error");
            System.out.println(result);
            return "create-student";
        }

        List<StudentDto> students = studentsWrapper.getList();

        List<StudentDto> studentsWithDupEnroll =
                studentService
                    .findStudentsWithEnrollNos(
                            mapListOfStudentDtosToListOfEnrollNos(students));

        List<StudentDto> studentsWithDupEmail =
                studentService
                    .findStudentsWithEmails(
                            mapListOfStudentDtosToListOfEmails(students));

        List<StudentDto> studentsWithDupMob =
                studentService
                    .findStudentsWithMobNos(
                            mapListOfStudentDtosToListOfMobNos(students));

        System.out.println("List Size: " + students.size());
        System.out
            .println("Dup Enroll List Size: " + studentsWithDupEnroll.size());
        System.out
            .println("Dup Email List Size: " + studentsWithDupEmail.size());
        System.out.println("Dup Mob List Size: " + studentsWithDupMob.size());

        if (!studentsWithDupEnroll.isEmpty()) {
            populateInfoInModelForDupEnrolls(model, studentsWithDupEnroll);
        }

        if (!studentsWithDupEmail.isEmpty()) {
            populateInfoInModelForDupEmails(model, studentsWithDupEmail);
        }

        if (!studentsWithDupMob.isEmpty()) {
            populateInfoInModelForDupMobNos(model, studentsWithDupMob);
        }

        // process only those students which do not have dup enroll or email or mob
        List<SemesterDto> semsFound =
                semService
                    .findSemByProgAndSemNo(
                            filterStudentDtosToSemDtosWhichAreNotDups(students,
                                    studentsWithDupEnroll,
                                    studentsWithDupEmail, studentsWithDupMob));

        System.out.println("Sems found" + semsFound);
        System.out.println("Students " + students);

        List<StudentDto> studWithIncorrProgDetails =
                filterStudentDtosHavingIncorrProgDetails(
                        filterStudentDtosWhichAreNotDups(students,
                                studentsWithDupEnroll, studentsWithDupEmail,
                                studentsWithDupMob),
                        semsFound);
        if (!studWithIncorrProgDetails.isEmpty()) {
            populateInfoInModelForIncorrProgDetails(model,
                    studWithIncorrProgDetails);
        }

        studentService
            .save(filterValidStudentDtos(students, studentsWithDupEnroll,
                    studentsWithDupEmail, studentsWithDupMob,
                    studWithIncorrProgDetails));
        if (studentsWithDupEnroll.isEmpty() && studentsWithDupEmail.isEmpty()
                && studentsWithDupMob.isEmpty()
                && studWithIncorrProgDetails.isEmpty()) {
            populateInfoInModelForCompleteSaveSuccess(model);
        } else {
            populateInfoInModelForPartialSaveSuccess(model);

        }
        populateInfoInModelForFreshSavePage(model);
        return "create-student";
    }

    private void populateInfoInModelForPartialSaveSuccess(Model model) {
        logger.info("Students are added partially");
        model
            .addAttribute("partialSaveSuccess",
                    "Some students are not save due to inconsistent data.");
    }

    private void populateInfoInModelForCompleteSaveSuccess(Model model) {
        logger.info("All the Students are added successfully");
        model
            .addAttribute("saveSuccessfull",
                    "All the Students are added successfully.");
    }

    private List<StudentDto> filterStudentDtosWhichAreNotDups(
            List<StudentDto> students,
            List<StudentDto> studentsWithDupEnroll,
            List<StudentDto> studentsWithDupEmail,
            List<StudentDto> studentsWithDupMob
    ) {
        return students
            .stream()
            .filter((s) -> !(doesListContainsEnrollInDto(studentsWithDupEnroll,
                    s) || doesListContainsEmailInDto(studentsWithDupEmail, s)
                    || doesListContainsMobInDto(studentsWithDupMob, s)))
            .collect(Collectors.toList());
    }

    private List<StudentDto> filterValidStudentDtos(
            List<StudentDto> students,
            List<StudentDto> studentsWithDupEnroll,
            List<StudentDto> studentsWithDupEmail,
            List<StudentDto> studentsWithDupMob,
            List<StudentDto> studWithIncorrProgDetails
    ) {
        return students
            .stream()
            .filter((s) -> !(doesListContainsEnrollInDto(studentsWithDupEnroll,
                    s) || doesListContainsEmailInDto(studentsWithDupEmail, s)
                    || doesListContainsMobInDto(studentsWithDupMob, s)
                    || doesListContainsSemDtoOfStudDto(
                            studWithIncorrProgDetails, s)))
            .collect(Collectors.toList());
    }

    private void populateInfoInModelForIncorrProgDetails(
            Model model,
            List<StudentDto> studWithIncorrProgDetails
    ) {
        model
            .addAttribute("studentsWithIncorrProg", studWithIncorrProgDetails);
        model
            .addAttribute("IncorrSaveError",
                    "Incorrect Programme(or Semester) details.");
        logger.warning("Bad combination of programme and semester.");
    }

    private void populateInfoInModelForDupEnrolls(
            Model model,
            List<StudentDto> studentsWithDupEnroll
    ) {
        model.addAttribute("studentsWithDupEnroll", studentsWithDupEnroll);
        model
            .addAttribute("dupEnrollSaveError",
                    "Student with given Enrollnent No allready exist in the database.");
        logger
            .warning(
                    "Students are tried for save with duplicate enrollment no.");
    }

    private void populateInfoInModelForDupEmails(
            Model model,
            List<StudentDto> studentsWithDupEmail
    ) {
        model.addAttribute("studentsWithDupEmail", studentsWithDupEmail);
        model
            .addAttribute("dupEmailSaveError",
                    "Student with given Email id allready exist in the database.");
        logger.warning("Students are tried for save with duplicate email id.");
    }

    private void populateInfoInModelForDupMobNos(
            Model model,
            List<StudentDto> studentsWithDupMob
    ) {
        model.addAttribute("studentsWithDupMob", studentsWithDupMob);
        model
            .addAttribute("dupMobSaveError",
                    "Student with given Mob No allready exist in the database.");
        logger.warning("Students are tried for save with duplicate Mob No.");
    }

    private List<StudentDto> filterStudentDtosHavingIncorrProgDetails(
            List<StudentDto> students,
            List<SemesterDto> semsFound
    ) {
        return students
            .stream()
            .filter((s) -> !semsFound.contains(DtoUtils.dtoToDtoFrom(s)))
            .collect(Collectors.toList());
    }

    private List<SemesterDto> filterStudentDtosToSemDtosWhichAreNotDups(
            List<StudentDto> students,
            List<StudentDto> studentsWithDupEnroll,
            List<StudentDto> studentsWithDupEmail,
            List<StudentDto> studentsWithDupMob
    ) {
        return students
            .stream()
            .filter((s) -> !(doesListContainsEnrollInDto(studentsWithDupEnroll,
                    s) || doesListContainsEmailInDto(studentsWithDupEmail, s)
                    || doesListContainsMobInDto(studentsWithDupMob, s)))
            .map(DtoUtils::dtoToDtoFrom)
            .collect(Collectors.toList());
    }

    private List<Long>
            mapListOfStudentDtosToListOfEnrollNos(List<StudentDto> students) {
        return students
            .stream()
            .map(StudentDto::getEnrollNo)
            .collect(Collectors.toList());
    }

    private List<String>
            mapListOfStudentDtosToListOfEmails(List<StudentDto> students) {
        return students
            .stream()
            .map(StudentDto::getEmail)
            .collect(Collectors.toList());
    }

    private List<Long>
            mapListOfStudentDtosToListOfMobNos(List<StudentDto> students) {
        return students
            .stream()
            .map(StudentDto::getMob)
            .collect(Collectors.toList());
    }

    private boolean doesListContainsEmailInDto(
            List<StudentDto> studentsWithDupEmail,
            StudentDto stud
    ) {
        return studentsWithDupEmail
            .stream()
            .map(StudentDto::getEmail)
            .anyMatch(e -> e.equals(stud.getEmail()));
    }

    private boolean doesListContainsEnrollInDto(
            List<StudentDto> studentsWithDupEnroll,
            StudentDto stud
    ) {
        return studentsWithDupEnroll
            .stream()
            .map(StudentDto::getEnrollNo)
            .anyMatch(e -> e.equals(stud.getEnrollNo()));
    }

    private boolean doesListContainsMobInDto(
            List<StudentDto> studentsWithDupMob,
            StudentDto stud
    ) {
        return studentsWithDupMob
            .stream()
            .map(StudentDto::getMob)
            .anyMatch(e -> e.equals(stud.getMob()));
    }

    private boolean doesListContainsSemDtoOfStudDto(
            List<StudentDto> studentsWithIncorrProg,
            StudentDto stud
    ) {
        return studentsWithIncorrProg
            .stream()
            .anyMatch(e -> e.getSemester().equals(stud.getSemester())
                    && e.getProgramme().equals(stud.getProgramme()));
    }
}