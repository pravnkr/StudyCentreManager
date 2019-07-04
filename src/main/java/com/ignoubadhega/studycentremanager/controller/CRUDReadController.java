package com.ignoubadhega.studycentremanager.controller;

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

import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.dto.StudentDto.EmailValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.EnrollNoValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.MobNoValidationGroup;
import com.ignoubadhega.studycentremanager.service.StudentService;
import com.ignoubadhega.studycentremanager.utils.Pair;

@Controller
public class CRUDReadController {

    private StudentService studentService;

    @Autowired
    public CRUDReadController(StudentService studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor =
                new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/staff/search-student")
    public String showDeleteStudentView() {
        return "search-student";
    }

    @GetMapping("/staff/search-student/by-enroll")
    public String setDeleteCriteriaToEnroll(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithEnroll");
        return "search-by-enroll";
    }

    @GetMapping("/staff/search-student/by-mob")
    public String setDeleteCriteriaToMob(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithMob");
        return "search-by-mob";
    }

    @GetMapping("/staff/search-student/by-email")
    public String setDeleteCriteriaToEmail(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithEmail");
        return "search-by-email";
    }

    @PostMapping("/staff/search-student/by-enroll/fetch")
    public String fetchRecordforDeleteByEnroll(
            @ModelAttribute("studentWithEnroll") @Validated(
                EnrollNoValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "search-by-enroll";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByEnrollNo(theStudent.getEnrollNo());
        if (student == null) {
            model.addAttribute("recordNotFetchedByEnroll", true);
            model
                .addAttribute("searchCriteriaAttributeVal",
                        theStudent.getEnrollNo());
        } else {
            model.addAttribute("recordFetchedByEnroll", true);
            model.addAttribute("fetchedStudentWithEnroll", student.getValue());
        }
        return "search-by-enroll";
    }

    @PostMapping("/staff/search-student/by-mob/fetch")
    public String fetchRecordforDeleteByMob(
            @ModelAttribute("studentWithMob") @Validated(
                MobNoValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "search-by-mob";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByMobNo(theStudent.getMob());
        if (student == null) {
            model.addAttribute("recordNotFetchedByMob", true);
            model
                .addAttribute("searchCriteriaAttributeVal",
                        theStudent.getMob());
        } else {
            model.addAttribute("recordFetchedByMob", true);
            model.addAttribute("fetchedStudentWithMob", student.getValue());
        }
        return "search-by-mob";
    }

    @PostMapping("/staff/search-student/by-email/fetch")
    public String fetchRecordforDeleteByEmail(
            @ModelAttribute("studentWithEmail") @Validated(
                EmailValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "search-by-email";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByEmail(theStudent.getEmail());
        if (student == null) {
            model.addAttribute("recordNotFetchedByEmail", true);
            model
                .addAttribute("searchCriteriaAttributeVal",
                        theStudent.getEmail());
        } else {
            model.addAttribute("recordFetchedByEmail", true);
            model.addAttribute("fetchedStudentWithEmail", student.getValue());
        }
        return "search-by-email";
    }

    private void
            populateModelWithFreshStudentDto(Model model, String modelParam) {
        StudentDto student = new StudentDto();
        model.addAttribute(modelParam, student);
    }
}
