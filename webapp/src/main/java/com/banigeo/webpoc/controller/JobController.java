package com.banigeo.webpoc.controller;

import com.banigeo.webpoc.dto.job.JobRequest;
import com.banigeo.webpoc.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/job")
@AllArgsConstructor
@Validated
public class JobController {

    private JobService jobService;

    @GetMapping("/list")
    public ModelAndView jobList() {
        ModelAndView mv = new ModelAndView("jobList");
        mv.addAllObjects(Map.of("jobs", jobService.getAllJobs()));
        return mv;
    }

    @RequestMapping("/registerForm")
    public String registrationForm(Model model) {
        model.addAllAttributes(Map.of("job", new JobRequest()));
        return "jobRegister";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute JobRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "jobRegister";
        }
        jobService.createJob(request);
        return "redirect:/job/list";
    }

}
