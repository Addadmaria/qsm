package com.universite.qsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSummaryDTO {
    private Long examId;
    private String title;
}
