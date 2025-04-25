package com.universite.qsm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "attempts")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(nullable = false)
    private LocalDateTime startedAt = LocalDateTime.now();

    private LocalDateTime finishedAt;

    private Double score;  // e.g. 85.00 for 85%

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Response> responses;

	public Long getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public LocalDateTime getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(LocalDateTime finishedAt) {
		this.finishedAt = finishedAt;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public Attempt() {
		this.exam = exam;
		this.student = student;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
		this.score = score;
		this.responses = responses;
	}

  
    
}
