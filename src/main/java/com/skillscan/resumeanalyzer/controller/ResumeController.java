package com.skillscan.resumeanalyzer.controller;

import com.skillscan.resumeanalyzer.model.Resume;
import com.skillscan.resumeanalyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ResumeController {
    @Autowired private ResumeService service;

    // Home page ughadnyasathi
    @GetMapping("/")
    public String home(){
        return "index";
    }

    // Resume Upload + Job Role
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("jobRole") String jobRole,
                             Model model){
        try{
            Resume r = service.processResume(file, jobRole);
            model.addAttribute("resume", r);
            model.addAttribute("jobRole", jobRole);
            model.addAttribute("score", r.getAtsScore());
            model.addAttribute("foundSkills", r.getSkillsFound());
            model.addAttribute("missingSkills", r.getMissingSkills());
            return "result";
        } catch(Exception e){
            model.addAttribute("error", "Error: " + e.getMessage());
            return "index";
        }
    }
}