package com.universite.qsm.controllers;


import lombok.RequiredArgsConstructor; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import com.universite.qsm.dtos.ChoiceDTO;
import com.universite.qsm.entities.Choice;
import com.universite.qsm.entities.Question;
import com.universite.qsm.repositories.ChoiceRepository;
import com.universite.qsm.repositories.QuestionRepository;

import java.util.List;

@RestController @RequestMapping("/api/choices") @RequiredArgsConstructor 
public class ChoiceController {


@Autowired
private ChoiceRepository choiceRepository;

@Autowired
private QuestionRepository questionRepository;

@PostMapping("/create")
public ResponseEntity<?> createChoice(@RequestBody ChoiceDTO dto) {
    Question question = questionRepository.findById(dto.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question introuvable"));

    Choice choice = new Choice();
    choice.setQuestion(question);
    choice.setText(dto.getText());
    choice.setIsCorrect(dto.getIsCorrect());

    choiceRepository.save(choice);
    return ResponseEntity.ok("Choix ajouté !");
}

@GetMapping("/all")
public List<Choice> getAllChoices() {
    return choiceRepository.findAll();
}
}