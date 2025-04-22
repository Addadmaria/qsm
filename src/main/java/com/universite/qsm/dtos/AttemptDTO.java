package com.universite.qsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptDTO {
    private Long attemptId;
    private Long examId;
    private Long studentId;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Double score;
    private List<ResponseDTO> responses;
	public Long getAttemptId() {
		return attemptId;
	}
	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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
	public List<ResponseDTO> getResponses() {
		return responses;
	}
	public void setResponses(List<ResponseDTO> responses) {
		this.responses = responses;
	}
	public AttemptDTO(Long examId, Long studentId, LocalDateTime startedAt, LocalDateTime finishedAt, Double score,
			List<ResponseDTO> responses) {
		super();
		this.examId = examId;
		this.studentId = studentId;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
		this.score = score;
		this.responses = responses;
	}
    
    
    
}
