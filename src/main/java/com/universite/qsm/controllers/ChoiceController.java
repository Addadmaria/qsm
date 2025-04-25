package com.universite.qsm.controllers;

import com.universite.qsm.dtos.ChoiceDTO;
import com.universite.qsm.entities.Choice;
import com.universite.qsm.entities.Question;
import com.universite.qsm.repositories.ChoiceRepository;
import com.universite.qsm.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/choices")
@CrossOrigin(origins = "*")
public class ChoiceController {

    @Autowired private ChoiceRepository choiceRepository;
    @Autowired private QuestionRepository questionRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createChoice(@RequestBody ChoiceDTO dto) {
        Question question = questionRepository.findById(dto.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question introuvable"));

        // If marking this choice as correct, ensure none exist yet
        if (Boolean.TRUE.equals(dto.getIsCorrect())) {
            long existing = choiceRepository
                .countByQuestionQuestionIdAndIsCorrect(dto.getQuestionId(), true);
            if (existing > 0) {
                return ResponseEntity
                    .badRequest()
                    .body("Il existe déjà un choix correct pour cette question");
            }
        }

        Choice c = Choice.builder()
                         .question(question)
                         .text(dto.getText())
                         .isCorrect(dto.getIsCorrect())
                         .build();
        choiceRepository.save(c);
        return ResponseEntity.ok("Choix ajouté !");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllChoices() {
        return ResponseEntity.ok(choiceRepository.findAll());
    }
}
