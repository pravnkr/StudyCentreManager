package com.ignoubadhega.studycentremanager.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.ignoubadhega.studycentremanager.dto.StudentDto;
import com.ignoubadhega.studycentremanager.dto.StudentDto.EmailValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.EnrollNoValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.MobNoValidationGroup;
import com.ignoubadhega.studycentremanager.service.StudentService;
import com.ignoubadhega.studycentremanager.utils.Pair;

@Controller
public class CRUDDeleteController {

    private StudentService studentService;

    @Autowired
    public CRUDDeleteController(StudentService studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor =
                new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/coordinator/delete-student")
    public String showDeleteStudentView() {
        return "delete-student";
    }

    @GetMapping("/coordinator/delete-student/by-enroll")
    public String setDeleteCriteriaToEnroll(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithEnroll");
        return "delete-by-enroll";
    }

    @GetMapping("/coordinator/delete-student/by-mob")
    public String setDeleteCriteriaToMob(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithMob");
        return "delete-by-mob";
    }

    @GetMapping("/coordinator/delete-student/by-email")
    public String setDeleteCriteriaToEmail(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithEmail");
        return "delete-by-email";
    }

    @PostMapping("/coordinator/delete-student/by-enroll/fetch")
    public String fetchRecordforDeleteByEnroll(
            @ModelAttribute("studentWithEnroll") @Validated(
                EnrollNoValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model,
            HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return "delete-by-enroll";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByEnrollNo(theStudent.getEnrollNo());
        if (student == null) {
            model.addAttribute("recordNotFetchedByEnroll", true);
            model
                .addAttribute("deleteCriteriaAttributeVal",
                        theStudent.getEnrollNo());
        } else {
            model.addAttribute("recordFetchedByEnroll", true);
            model.addAttribute("fetchedStudentWithEnroll", student.getValue());
            request.setAttribute("id", student.getId());
        }
        return "delete-by-enroll";
    }

    @PostMapping("/coordinator/delete-student/by-mob/fetch")
    public String fetchRecordforDeleteByMob(
            @ModelAttribute("studentWithMob") @Validated(
                MobNoValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "delete-by-mob";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByMobNo(theStudent.getMob());
        if (student == null) {
            model.addAttribute("recordNotFetchedByMob", true);
            model
                .addAttribute("deleteCriteriaAttributeVal",
                        theStudent.getMob());
        } else {
            model.addAttribute("recordFetchedByMob", true);
            model.addAttribute("fetchedStudentWithMob", student.getValue());
            model.addAttribute("id", student.getId());
        }
        return "delete-by-mob";
    }

    @PostMapping("/coordinator/delete-student/by-email/fetch")
    public String fetchRecordforDeleteByEmail(
            @ModelAttribute("studentWithEmail") @Validated(
                EmailValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "delete-by-email";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByEmail(theStudent.getEmail());
        if (student == null) {
            model.addAttribute("recordNotFetchedByEmail", true);
            model
                .addAttribute("deleteCriteriaAttributeVal",
                        theStudent.getEmail());
        } else {
            model.addAttribute("recordFetchedByEmail", true);
            model.addAttribute("fetchedStudentWithEmail", student.getValue());
            model.addAttribute("id", student.getId());
        }
        return "delete-by-email";
    }

    @PostMapping("/coordinator/delete-student/by-enroll/save")
    public String deleteStudentByEnroll(
            @ModelAttribute("fetchedStudentWithEnroll") StudentDto theStudent,
            @RequestParam("id") Long id,
            Model model
    ) {
        studentService.delete(id);
        populateInfoInModelForDeleteSuccess(theStudent, model);
        return "delete-success-confirmation";
    }

    @PostMapping("/coordinator/delete-student/by-mob/save")
    public String updateStudentByMob(
            @ModelAttribute("fetchedStudentWithMob") StudentDto theStudent,
            @RequestParam("id") Long id,
            Model model
    ) {
        studentService.delete(id);
        populateInfoInModelForDeleteSuccess(theStudent, model);
        return "delete-success-confirmation";
    }

    @PostMapping("/coordinator/delete-student/by-email/save")
    public String updateStudentByEmail(
            @ModelAttribute("fetchedStudentWithEmail") StudentDto theStudent,
            @RequestParam("id") Long id,
            Model model
    ) {
        studentService.delete(id);
        populateInfoInModelForDeleteSuccess(theStudent, model);
        return "delete-success-confirmation";
    }

    private void populateInfoInModelForDeleteSuccess(
            StudentDto theStudent,
            Model model
    ) {
        model.addAttribute("deleteSuccess", true);
        model.addAttribute("deletedStudent", theStudent);
    }

    private void
            populateModelWithFreshStudentDto(Model model, String modelParam) {
        StudentDto student = new StudentDto();
        model.addAttribute(modelParam, student);
    }

}
