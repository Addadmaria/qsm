package com.universite.qsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultDTO {
    private Long examId;
    private String title;
    private List<StudentScoreDTO> results;
}
