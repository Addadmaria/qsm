package com.universite.qsm.controllers;

import com.universite.qsm.dtos.QuestionDTO;
import com.universite.qsm.entities.Exam;
import com.universite.qsm.entities.Question;
import com.universite.qsm.repositories.ExamRepository;
import com.universite.qsm.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    @PostMapping("/create")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO dto) {
        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new RuntimeException("Examen introuvable"));

        Question q = new Question();
        q.setExam(exam);
        q.setText(dto.getText());
        Question saved = questionRepository.save(q);

        QuestionDTO response = new QuestionDTO();
        response.setQuestionId(saved.getQuestionId());
        response.setExamId(exam.getExamId());
        response.setText(saved.getText());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
