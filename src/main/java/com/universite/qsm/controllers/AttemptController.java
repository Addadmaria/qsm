package com.universite.qsm.controllers;


import lombok.RequiredArgsConstructor; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import com.universite.qsm.dtos.AttemptDTO;
import com.universite.qsm.entities.Attempt;
import com.universite.qsm.entities.Exam;
import com.universite.qsm.entities.User;
import com.universite.qsm.repositories.AttemptRepository;
import com.universite.qsm.repositories.ExamRepository;
import com.universite.qsm.repositories.UserRepository;

import java.time.LocalDateTime; import java.util.List;

@RestController @RequestMapping("/api/attempts") @RequiredArgsConstructor 
public class AttemptController {


    @Autowired private AttemptRepository attemptRepository;
    @Autowired private ExamRepository examRepository;
    @Autowired private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<AttemptDTO> create(@RequestBody AttemptDTO dto) {
        Exam exam    = examRepository.findById(dto.getExamId())
                          .orElseThrow();
        User student = userRepository.findById(dto.getStudentId())
                          .orElseThrow();

        Attempt at = new Attempt();
        at.setExam(exam);
        at.setStudent(student);
        at.setStartedAt(LocalDateTime.now());
        Attempt saved = attemptRepository.save(at);

        AttemptDTO out = new AttemptDTO();
        out.setAttemptId(saved.getAttemptId());
        out.setExamId(exam.getExamId());
        out.setStudentId(student.getUserId());
        out.setStartedAt(saved.getStartedAt());
        return ResponseEntity.ok(out);
    }

@GetMapping("/all")
public List<Attempt> getAllAttempts() {
    return attemptRepository.findAll();
}
}