package com.universite.qsm.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "choices")              // ← removed uniqueConstraints
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private String text;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect = false;
}
