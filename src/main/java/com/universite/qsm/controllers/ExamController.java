package com.universite.qsm.controllers;

import lombok.RequiredArgsConstructor; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.universite.qsm.dtos.ExamDTO;
import com.universite.qsm.dtos.ExamSummaryDTO;
import com.universite.qsm.dtos.UserDTO;
import com.universite.qsm.entities.Exam;
import com.universite.qsm.entities.User;
import com.universite.qsm.repositories.ExamRepository;
import com.universite.qsm.repositories.UserRepository;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController @RequestMapping("/api/exams") 
public class ExamController {
	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody ExamDTO dto) {
        // 1) Load the user
        User creator = userRepository.findById(dto.getCreatedBy())
          .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        // 2) Server-side authorization: only MENTOR can create exams
        if (creator.getRole() != User.Role.MENTOR) {
            return ResponseEntity
              .status(HttpStatus.FORBIDDEN)
              .body("Accès refusé: rôle mentor requis");
        }

        // 3) Proceed as before
        Exam exam = new Exam();
        exam.setTitle(dto.getTitle());
        exam.setDescription(dto.getDescription());
        exam.setCreatedBy(creator);
        exam.setCreatedAt(LocalDateTime.now());
        exam.setUpdatedAt(LocalDateTime.now());

        examRepository.save(exam);
        return ResponseEntity.ok("Examen créé avec succès !");
    }

	@GetMapping("/all")
	public List<Exam> getAllExams() {
	    return examRepository.findAll();
	}

	@GetMapping("/exams")
    public List<ExamSummaryDTO> getExams() {
        return examRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ExamSummaryDTO toDto(Exam e) {
        // only expose id & title
        return new ExamSummaryDTO(e.getExamId(), e.getTitle());
    }

}