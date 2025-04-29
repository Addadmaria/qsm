package com.universite.qsm.controllers;

import com.universite.qsm.dtos.ExamResultDTO;
import com.universite.qsm.dtos.StudentScoreDTO;
import com.universite.qsm.entities.Attempt;
import com.universite.qsm.entities.User;
import com.universite.qsm.repositories.AttemptRepository;
import com.universite.qsm.repositories.ExamRepository;
import com.universite.qsm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExamResultController {

    @Autowired private ExamRepository   examRepository;
    @Autowired private AttemptRepository attemptRepository;
    @Autowired private UserRepository     userRepository;

    /**
     * GET /api/exams/my-results?mentorId=...
     * Returns all exams created by this mentor, each with student scores.
     */
    @GetMapping("/my-results")
    public List<ExamResultDTO> myResults(@RequestParam Long mentorId) {
        // 1) fetch exams for this mentor
        return examRepository.findByCreatedByUserId(mentorId).stream().map(exam -> {
            // 2) For each exam, fetch its attempts
            List<Attempt> atts = attemptRepository.findByExamExamId(exam.getExamId());
            // 3) build student-score list
            List<StudentScoreDTO> scores = atts.stream().map(a -> {
                User s = a.getStudent();
                return new StudentScoreDTO(
                    s.getUserId(),
                    s.getName(),
                    a.getScore()
                );
            }).collect(Collectors.toList());
            // 4) assemble DTO
            return new ExamResultDTO(
                exam.getExamId(),
                exam.getTitle(),
                scores
            );
        }).collect(Collectors.toList());
    }
}
