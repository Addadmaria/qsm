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


@Autowired
private AttemptRepository attemptRepository;

@Autowired
private UserRepository userRepository;

@Autowired
private ExamRepository examRepository;

@PostMapping("/create")
public ResponseEntity<?> createAttempt(@RequestBody AttemptDTO dto) {
    User student = userRepository.findById(dto.getStudentId())
            .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
    Exam exam = examRepository.findById(dto.getExamId())
            .orElseThrow(() -> new RuntimeException("Examen introuvable"));

    Attempt attempt = new Attempt();
    attempt.setExam(exam);
    attempt.setStudent(student);
    attempt.setStartedAt(LocalDateTime.now());

    attemptRepository.save(attempt);
    return ResponseEntity.ok("Tentative enregistrée !");
}

@GetMapping("/all")
public List<Attempt> getAllAttempts() {
    return attemptRepository.findAll();
}
}