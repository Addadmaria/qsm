package com.universite.qsm.controllers;


import lombok.RequiredArgsConstructor; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import com.universite.qsm.dtos.QuestionDTO;
import com.universite.qsm.entities.Exam;
import com.universite.qsm.entities.Question;
import com.universite.qsm.repositories.ExamRepository;
import com.universite.qsm.repositories.QuestionRepository;

import java.util.List;

@RestController @RequestMapping("/api/questions") @RequiredArgsConstructor 
public class QuestionController {


@Autowired
private QuestionRepository questionRepository;

@Autowired
private ExamRepository examRepository;

@PostMapping("/create")
public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO dto) {
    Exam exam = examRepository.findById(dto.getExamId())
            .orElseThrow(() -> new RuntimeException("Examen introuvable"));

    Question q = new Question();
    q.setExam(exam);
    q.setText(dto.getText());
    q.setSortOrder(dto.getSortOrder());

    questionRepository.save(q);
    return ResponseEntity.ok("Question ajoutée avec succès !");
}

@GetMapping("/all")
public List<Question> getAllQuestions() {
    return questionRepository.findAll();
}
}