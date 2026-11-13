package com.skillscan.resumeanalyzer.service;

import com.skillscan.resumeanalyzer.model.Resume;
import com.skillscan.resumeanalyzer.repository.ResumeRepository;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class ResumeService {
    @Autowired private ResumeRepository repo;

    // NAVIN: (file, jobRole) - jobRole add kela
    public Resume processResume(MultipartFile file, String jobRole) throws IOException {
        String text = "";
        try(PDDocument doc = Loader.loadPDF(file.getBytes())){
            text = new PDFTextStripper().getText(doc);
        }
        String lower = text.toLowerCase();

        // NAVIN: Role nusar skills nivad
        String[] allSkills = getSkillsForRole(jobRole);

        List<String> found = new ArrayList<>();
        List<String> missing = new ArrayList<>();
        for(String s: allSkills){
            if(lower.contains(s)) found.add(s.toUpperCase());
            else missing.add(s.toUpperCase());
        }
        // Score: 40 base + found * (55 / total skills)
        int score = 40 + (found.size() * 55 / allSkills.length);
        if(score>95) score=95;

        Resume r = new Resume();
        r.setFileName(file.getOriginalFilename());
        r.setExtractedText(text);
        r.setSkillsFound(String.join(", ", found));
        r.setMissingSkills(String.join(", ", missing));
        r.setAtsScore(score);
        return repo.save(r);
    }

    // NAVIN: He navin method khali add kela - Role nusar skills
    private String[] getSkillsForRole(String jobRole) {
        if (jobRole == null) jobRole = "BACKEND";
        switch (jobRole) {
            case "FRONTEND":
                return new String[]{"html","css","javascript","react","angular","typescript","bootstrap","node.js","git","github"};
            case "DATA_ANALYST":
                return new String[]{"python","sql","excel","power bi","pandas","mysql","tableau","statistics","data visualization","git"};
            case "AI_ML":
                return new String[]{"python","machine learning","deep learning","tensorflow","pytorch","nlp","pandas","numpy","sql","git"};
            case "BACKEND":
            default:
                return new String[]{"java","spring","sql","mysql","mongodb","rest api","git","github","docker","aws"};
        }
    }
}