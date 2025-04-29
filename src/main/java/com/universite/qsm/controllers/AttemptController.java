package com.universite.qsm.controllers;

import com.universite.qsm.dtos.AttemptDTO;
import com.universite.qsm.entities.Attempt;
import com.universite.qsm.entities.Exam;
import com.universite.qsm.entities.User;
import com.universite.qsm.entities.Response;
import com.universite.qsm.repositories.AttemptRepository;
import com.universite.qsm.repositories.ExamRepository;
import com.universite.qsm.repositories.ResponseRepository;
import com.universite.qsm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attempts")
@CrossOrigin(origins = "*")
public class AttemptController {

    @Autowired private AttemptRepository attemptRepository;
    @Autowired private ExamRepository    examRepository;
    @Autowired private UserRepository    userRepository;
    @Autowired private ResponseRepository responseRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AttemptDTO dto) {
        User student = userRepository.findById(dto.getStudentId())
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        if (student.getRole() != User.Role.STUDENT) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Accès refusé : rôle student requis");
        }
        Exam exam = examRepository.findById(dto.getExamId())
            .orElseThrow(() -> new RuntimeException("Examen introuvable"));

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

    @GetMapping("/{attemptId}/score")
    public ResponseEntity<?> getScore(@PathVariable Long attemptId) {
        Attempt at = attemptRepository.findById(attemptId)
            .orElseThrow(() -> new RuntimeException("Tentative introuvable"));

        // Count correct responses
        List<Response> resp = responseRepository.findByAttemptAttemptId(attemptId);
        long correctCount = resp.stream()
            .filter(r -> Boolean.TRUE.equals(r.getChoice().getIsCorrect()))
            .count();

        // Total questions in the exam
        long totalQuestions = at.getExam().getQuestions().size();
        // If you hid questions in JSON, you can use QuestionRepository.countByExamExamId

        double note = ((double) correctCount * 20.0) / totalQuestions;
        at.setFinishedAt(LocalDateTime.now());
        at.setScore(note);
        attemptRepository.save(at);

        Map<String,Object> body = new HashMap<>();
        body.put("correct", correctCount);
        body.put("total", totalQuestions);
        body.put("score", note);
        return ResponseEntity.ok(body);
    }
}
