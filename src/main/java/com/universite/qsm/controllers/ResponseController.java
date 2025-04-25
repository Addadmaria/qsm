package com.universite.qsm.controllers;



import lombok.RequiredArgsConstructor; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import com.universite.qsm.dtos.ResponseDTO;
import com.universite.qsm.entities.Attempt;
import com.universite.qsm.entities.Choice;
import com.universite.qsm.entities.Question;
import com.universite.qsm.entities.Response;
import com.universite.qsm.repositories.AttemptRepository;
import com.universite.qsm.repositories.ChoiceRepository;
import com.universite.qsm.repositories.QuestionRepository;
import com.universite.qsm.repositories.ResponseRepository;

import java.time.LocalDateTime; import java.util.List;

@RestController @RequestMapping("/api/responses")@CrossOrigin(origins = "*") 
public class ResponseController {


@Autowired
private ResponseRepository responseRepository;

@Autowired
private AttemptRepository attemptRepository;

@Autowired
private QuestionRepository questionRepository;

@Autowired
private ChoiceRepository choiceRepository;

@PostMapping("/create")
public ResponseEntity<?> createResponse(@RequestBody ResponseDTO dto) {
    Attempt attempt = attemptRepository.findById(dto.getAttemptId())
            .orElseThrow(() -> new RuntimeException("Tentative introuvable"));
    Question question = questionRepository.findById(dto.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question introuvable"));
    Choice choice = choiceRepository.findById(dto.getChoiceId())
            .orElseThrow(() -> new RuntimeException("Choix introuvable"));

    Response response = new Response();
    response.setAttempt(attempt);
    response.setQuestion(question);
    response.setChoice(choice);
    response.setAnsweredAt(LocalDateTime.now());

    responseRepository.save(response);
    return ResponseEntity.ok("Réponse enregistrée !");
}

@GetMapping("/all")
public List<Response> getAllResponses() {
    return responseRepository.findAll();
}
}