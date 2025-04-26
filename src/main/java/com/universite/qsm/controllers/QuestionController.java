package com.universite.qsm.controllers;

import com.universite.qsm.dtos.QuestionDTO;
import com.universite.qsm.dtos.QuestionWithChoicesDTO;
import com.universite.qsm.entities.Exam;
import com.universite.qsm.entities.Question;
import com.universite.qsm.repositories.ExamRepository;
import com.universite.qsm.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/exam/{examId}")
    public List<QuestionWithChoicesDTO> getByExam(@PathVariable Long examId) {
        return questionRepository
            .findByExamExamId(examId)
            .stream()
            .map(q -> {
              QuestionWithChoicesDTO dto = new QuestionWithChoicesDTO();
              dto.setQuestionId(q.getQuestionId());
              dto.setText(q.getText());
              dto.setChoices(
                q.getChoices()
                 .stream()
                 .map(c -> new com.universite.qsm.dtos.ChoiceDTO(
                    c.getChoiceId(),
                    q.getQuestionId(),
                    c.getText(),
                    null // don’t expose correctness
                 )).collect(Collectors.toList())
              );
              return dto;
            }).collect(Collectors.toList());
    }
}
