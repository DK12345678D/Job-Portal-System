package com.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Application;
import com.entity.Job;
import com.entity.User;
import com.repository.ApplicationRepository;
import com.repository.JobRepository;
import com.repository.UserRepository;

@Controller
public class ApplicantController {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jobs")
    public String viewAllJobs(@RequestParam(required = false) String keyword, Model model) {
        List<Job> jobs = keyword == null ? jobRepository.findAll() :
            jobRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @PostMapping("/apply/{jobId}")
    public String applyJob(@PathVariable Long jobId, Principal principal) {
        User applicant = userRepository.findByUsername(principal.getName());
        Job job = jobRepository.findById(jobId).orElseThrow();

        Application app = new Application();
        app.setApplicant(applicant);
        app.setJob(job);
        app.setStatus("APPLIED");
        applicationRepository.save(app);

        return "redirect:/applications";
    }

    @GetMapping("/applications")
    public String viewApplications(Principal principal, Model model) {
        User applicant = userRepository.findByUsername(principal.getName());
        model.addAttribute("applications", applicationRepository.findByApplicant(applicant));
        return "applications";
    }
}