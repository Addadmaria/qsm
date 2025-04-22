package com.universite.qsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universite.qsm.entities.Exam;
import com.universite.qsm.entities.User;





@Repository
public interface ExamRepository extends JpaRepository< Exam , Long > {

	
	
}
