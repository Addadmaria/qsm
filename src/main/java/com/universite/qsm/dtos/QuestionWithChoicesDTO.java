package com.universite.qsm.dtos;

import lombok.Data;
import java.util.List;

@Data
public class QuestionWithChoicesDTO {
    private Long questionId;
    private String text;
    private List<ChoiceDTO> choices;
}
