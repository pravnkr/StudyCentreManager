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
import com.ignoubadhega.studycentremanager.dto.StudentDto.DefaultValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.EmailValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.EnrollNoValidationGroup;
import com.ignoubadhega.studycentremanager.dto.StudentDto.MobNoValidationGroup;
import com.ignoubadhega.studycentremanager.service.StudentService;
import com.ignoubadhega.studycentremanager.utils.Pair;

@Controller
public class CRUDUpdateController {
    
    private StudentService studentService;
    
    @Autowired
    public CRUDUpdateController(StudentService studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor =
                new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    
    @GetMapping("/coordinator/update-student")
    public String showUpdateStudentView() {
        return "update-student";
    }

    @GetMapping("/coordinator/update-student/by-enroll")
    public String setUpdateCriteriaToEnroll(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithEnroll");
        return "update-by-enroll";
    }

    @GetMapping("/coordinator/update-student/by-mob")
    public String setUpdateCriteriaToMob(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithMob");
        return "update-by-mob";
    }

    @GetMapping("/coordinator/update-student/by-email")
    public String setUpdateCriteriaToEmail(Model model) {
        populateModelWithFreshStudentDto(model, "studentWithEmail");
        return "update-by-email";
    }

    @PostMapping("/coordinator/update-student/by-enroll/fetch")
    public String fetchRecordforUpdateByEnroll(
            @ModelAttribute("studentWithEnroll") @Validated(
                EnrollNoValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model,
            HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return "update-by-enroll";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByEnrollNo(theStudent.getEnrollNo());
        if (student == null) {
            model.addAttribute("recordNotFetchedByEnroll", true);
            model
                .addAttribute("updateCriteriaAttributeVal",
                        theStudent.getEnrollNo());
        } else {
            model.addAttribute("recordFetchedByEnroll", true);
            model.addAttribute("fetchedStudentWithEnroll", student.getValue());
            request.setAttribute("id", student.getId());
        }
        return "update-by-enroll";
    }

    @PostMapping("/coordinator/update-student/by-mob/fetch")
    public String fetchRecordforUpdateByMob(
            @ModelAttribute("studentWithMob") @Validated(
                MobNoValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "update-by-mob";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByMobNo(theStudent.getMob());
        if (student == null) {
            model.addAttribute("recordNotFetchedByMob", true);
            model
                .addAttribute("updateCriteriaAttributeVal",
                        theStudent.getMob());
        } else {
            model.addAttribute("recordFetchedByMob", true);
            model.addAttribute("fetchedStudentWithMob", student.getValue());
            model.addAttribute("id", student.getId());
        }
        return "update-by-mob";
    }

    @PostMapping("/coordinator/update-student/by-email/fetch")
    public String fetchRecordforUpdateByEmail(
            @ModelAttribute("studentWithEmail") @Validated(
                EmailValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "update-by-email";
        }
        Pair<Long, StudentDto> student =
                studentService.findStudentByEmail(theStudent.getEmail());
        if (student == null) {
            model.addAttribute("recordNotFetchedByEmail", true);
            model
                .addAttribute("updateCriteriaAttributeVal",
                        theStudent.getEmail());
        } else {
            model.addAttribute("recordFetchedByEmail", true);
            model.addAttribute("fetchedStudentWithEmail", student.getValue());
            model.addAttribute("id", student.getId());
        }
        return "update-by-email";
    }

    @PostMapping("/coordinator/update-student/by-enroll/save")
    public String updateStudentByEnroll(
            @ModelAttribute("fetchedStudentWithEnroll") @Validated(
                DefaultValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            @RequestParam("id") Long id,
            Model model,
            HttpServletRequest request
    ) {
        System.out.println("Map" + model.asMap() + "\n" + theStudent + "\n");
        if (result.hasErrors()) {
            System.out.println("Error2");
            model.addAttribute("inputError", true);
            model.addAttribute("studentWithEnroll", new StudentDto());
            request.setAttribute("id", id);
            return "update-by-enroll";
        }
        Pair<Long, StudentDto> retrievedStudByEnroll =
                studentService.findStudentByEnrollNo(theStudent.getEnrollNo());
        Pair<Long, StudentDto> retrievedStudByEmail =
                studentService.findStudentByEmail(theStudent.getEmail());
        Pair<Long, StudentDto> retrievedStudByMob =
                studentService.findStudentByMobNo(theStudent.getMob());

        if (setupModelInfoForUpdateConflictDueToExistingStudent(id, model,
                retrievedStudByEnroll, retrievedStudByEmail,
                retrievedStudByMob)) {
            model.addAttribute("studentWithEnroll", new StudentDto());
            return "update-by-enroll";
        }
        Pair<Long, StudentDto> student = new Pair<>(id, theStudent);
        studentService.update(student);
        populateInfoInModelForUpdateSuccess(theStudent, model);
        return "update-success-confirmation";
    }

    @PostMapping("/coordinator/update-student/by-mob/save")
    public String updateStudentByMob(
            @ModelAttribute("fetchedStudentWithMob") @Validated(
                DefaultValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            @RequestParam("id") Long id,
            Model model
    ) {
        System.out.println("Map" + model.asMap() + "\n" + theStudent + "\n");
        if (result.hasErrors()) {
            System.out.println("Error2");
            model.addAttribute("inputError", true);
            model.addAttribute("studentWithMob", new StudentDto());
            return "update-by-mob";
        }
        Pair<Long, StudentDto> retrievedStudByEnroll =
                studentService.findStudentByEnrollNo(theStudent.getEnrollNo());
        Pair<Long, StudentDto> retrievedStudByEmail =
                studentService.findStudentByEmail(theStudent.getEmail());
        Pair<Long, StudentDto> retrievedStudByMob =
                studentService.findStudentByMobNo(theStudent.getMob());

        if (setupModelInfoForUpdateConflictDueToExistingStudent(id, model,
                retrievedStudByEnroll, retrievedStudByEmail,
                retrievedStudByMob)) {
            model.addAttribute("studentWithMob", new StudentDto());
            return "update-by-mob";
        }
        Pair<Long, StudentDto> student = new Pair<>(id, theStudent);
        studentService.update(student);
        populateInfoInModelForUpdateSuccess(theStudent, model);
        return "update-success-confirmation";
    }

    @PostMapping("/coordinator/update-student/by-email/save")
    public String updateStudentByEmail(
            @ModelAttribute("fetchedStudentWithEmail") @Validated(
                DefaultValidationGroup.class
            ) StudentDto theStudent,
            BindingResult result,
            @RequestParam("id") Long id,
            Model model
    ) {
        System.out.println("Map" + model.asMap() + "\n" + theStudent + "\n");
        if (result.hasErrors()) {
            System.out.println("Error2");
            model.addAttribute("inputError", true);
            model.addAttribute("studentWithEmail", new StudentDto());
            return "update-by-email";
        }
        Pair<Long, StudentDto> retrievedStudByEnroll =
                studentService.findStudentByEnrollNo(theStudent.getEnrollNo());
        Pair<Long, StudentDto> retrievedStudByEmail =
                studentService.findStudentByEmail(theStudent.getEmail());
        Pair<Long, StudentDto> retrievedStudByMob =
                studentService.findStudentByMobNo(theStudent.getMob());

        if (setupModelInfoForUpdateConflictDueToExistingStudent(id, model,
                retrievedStudByEnroll, retrievedStudByEmail,
                retrievedStudByMob)) {
            model.addAttribute("studentWithEmail", new StudentDto());
            return "update-by-email";
        }
        Pair<Long, StudentDto> student = new Pair<>(id, theStudent);
        studentService.update(student);
        populateInfoInModelForUpdateSuccess(theStudent, model);
        return "update-success-confirmation";
    }

    private void populateInfoInModelForUpdateSuccess(
            StudentDto theStudent,
            Model model
    ) {
        model.addAttribute("saveSuccess", true);
        model.addAttribute("updatedStudent", theStudent);
    }

    private boolean setupModelInfoForUpdateConflictDueToExistingStudent(
            Long id,
            Model model,
            Pair<Long, StudentDto> retrievedStudByEnroll,
            Pair<Long, StudentDto> retrievedStudByEmail,
            Pair<Long, StudentDto> retrievedStudByMob
    ) {
        if (!(retrievedStudByEnroll == null
                || retrievedStudByEnroll.getId().equals(id))) {
            model.addAttribute("updateError", true);
            model
                .addAttribute("existingStudent",
                        retrievedStudByEnroll.getValue());
            System.out.println("Dup Enroll");
            return true;
        } else if (!(retrievedStudByEmail == null
                || retrievedStudByEmail.getId().equals(id))) {
            model.addAttribute("updateError", true);
            model
                .addAttribute("existingStudent",
                        retrievedStudByEmail.getValue());
            System.out.println("Dup Email");
            return true;
        } else if (!(retrievedStudByMob == null
                || retrievedStudByMob.getId().equals(id))) {
            model.addAttribute("updateError", true);
            model
                .addAttribute("existingStudent",
                        retrievedStudByMob.getValue());
            System.out.println("Dup Mob");
            return true;
        }
        return false;
    }

    private void
            populateModelWithFreshStudentDto(Model model, String modelParam) {
        StudentDto student = new StudentDto();
        model.addAttribute(modelParam, student);
    }
}
