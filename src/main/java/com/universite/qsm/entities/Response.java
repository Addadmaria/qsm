package com.universite.qsm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "responses")

public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attempt_id", nullable = false)
    private Attempt attempt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "choice_id", nullable = false)
    private Choice choice;

    @Column(nullable = false)
    private LocalDateTime answeredAt = LocalDateTime.now();

	public Long getResponseId() {
		return responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}

	public Attempt getAttempt() {
		return attempt;
	}

	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

	public LocalDateTime getAnsweredAt() {
		return answeredAt;
	}

	public void setAnsweredAt(LocalDateTime answeredAt) {
		this.answeredAt = answeredAt;
	}

	public Response() {
		this.attempt = attempt;
		this.question = question;
		this.choice = choice;
		this.answeredAt = answeredAt;
	}

    // ... constructors, getters/setters
    
}
