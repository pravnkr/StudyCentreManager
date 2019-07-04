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

import com.ignoubadhega.studycentremanager.dto.StudentDto.DefaultValidationGroup;
import com.ignoubadhega.studycentremanager.dto.TeacherDto;
import com.ignoubadhega.studycentremanager.service.SubjectService;
import com.ignoubadhega.studycentremanager.service.TeacherService;

@Controller
public class TeacherController {

    @Autowired
    private SubjectService subService;
    
    @Autowired
    private TeacherService teacherService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor =
                new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/coordinator/add-teacher")
    public String showTeacherAddForm(Model model) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setSubjects(subService.findAllSubjectCodes());
        model.addAttribute("teacher", teacherDto);
        return "add-teacher";
    }

    @PostMapping("/coordinator/add-teacher/save")
    public String saveTeacher(
            @ModelAttribute("teacher") @Validated(
                DefaultValidationGroup.class
            ) TeacherDto theTeacher,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "add-teacher";
        }
        
        TeacherDto teacherExist = teacherService.teacherExist(theTeacher);
        if (teacherExist != null) {
            model.addAttribute("dupSaveError", true);
            model.addAttribute("dupTeacher", teacherExist);
            return "add-teacher";
        }
        teacherService.save(theTeacher);
        model.addAttribute("saveSuccess", true);
        return "add-teacher";
    }

}
