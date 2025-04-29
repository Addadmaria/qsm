package com.universite.qsm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universite.qsm.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository< Question , Long > {
	List<Question> findByExamExamId(Long examId);
    long countByExamExamId(Long examId);
}
