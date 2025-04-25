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
public class ExamDTO {
    private Long examId;
    private String title;
    private String description;
    private Long createdBy; 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<QuestionDTO> questions;
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
	public ExamDTO(String title, String description, Long createdBy, LocalDateTime createdAt, LocalDateTime updatedAt,
			List<QuestionDTO> questions) {
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.questions = questions;
	}
    
    
}
