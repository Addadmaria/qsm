package com.universite.qsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {
    private Long questionId;
    private Long examId;
    private String text;
    private Integer sortOrder;
    private List<ChoiceDTO> choices;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public List<ChoiceDTO> getChoices() {
		return choices;
	}
	public void setChoices(List<ChoiceDTO> choices) {
		this.choices = choices;
	}
	public QuestionDTO(Long examId, String text, Integer sortOrder, List<ChoiceDTO> choices) {
		super();
		this.examId = examId;
		this.text = text;
		this.sortOrder = sortOrder;
		this.choices = choices;
	}
    
    
}
