package com.universite.qsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreDTO {
    private Long studentId;
    private String studentName;
    private Double score;
}
