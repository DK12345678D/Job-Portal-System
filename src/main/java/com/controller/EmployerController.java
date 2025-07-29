package com.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Job;
import com.entity.User;
import com.repository.JobRepository;
import com.repository.UserRepository;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jobs")
    public String viewJobs(Model model, Principal principal) {
        User employer = userRepository.findByUsername(principal.getName());
        model.addAttribute("jobs", jobRepository.findByEmployer(employer));
        return "employer-jobs";
    }

    @GetMapping("/post-job")
    public String postJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "post-job";
    }

    @PostMapping("/jobs")
    public String postJob(@ModelAttribute Job job, Principal principal) {
        User employer = userRepository.findByUsername(principal.getName());
        job.setEmployer(employer);
        jobRepository.save(job);
        return "redirect:/employer/jobs";
    }
}
