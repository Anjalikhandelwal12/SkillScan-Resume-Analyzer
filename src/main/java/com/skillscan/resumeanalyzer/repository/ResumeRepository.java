package com.skillscan.resumeanalyzer.repository;

import com.skillscan.resumeanalyzer.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {}