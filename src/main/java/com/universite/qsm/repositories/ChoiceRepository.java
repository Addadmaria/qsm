package com.universite.qsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universite.qsm.entities.Choice;




@Repository
public interface ChoiceRepository extends JpaRepository< Choice , Long > {

    long countByQuestionQuestionIdAndIsCorrect(Long questionId, Boolean isCorrect);
	
}
