package com.ignoubadhega.studycentremanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ignoubadhega.studycentremanager.dto.SemesterDto;
import com.ignoubadhega.studycentremanager.service.BatchService;

@Controller
public class BatchController {

    @Autowired
    BatchService batchService;

    @GetMapping("/coordinator/generate-batch")
    public String showBatchGeneratorPage(Model model) {
        model.addAttribute("semester", new SemesterDto());
        return "generate-batch";
    }

    @PostMapping("/coordinator/generate-batch/apply")
    public String generateBatch(
            @ModelAttribute("semester") SemesterDto semester,
            Model model
    ) {
        List<String> batchNos = batchService.generateBatchFor(semester);
        if (batchNos == null || batchNos.isEmpty()) {
            model
                .addAttribute("batchGeneratorError",
                        "Their is some error in generating a batch.");
            return "generate-batch";
        }
        model.addAttribute("batchNos", batchNos);
        model
            .addAttribute("batchGeneratorSuccess",
                    "Batch Generated Successfully for "
                        + semester.getProgramme()
                        + " "
                        + semester.getSemNo()
                        + " Semester");
        model.addAttribute("semester", new SemesterDto());
        return "generate-batch";
    }

}
