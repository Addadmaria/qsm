package com.universite.qsm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universite.qsm.entities.Attempt;



@Repository
public interface AttemptRepository extends JpaRepository< Attempt , Long > {
	List<Attempt> findByExamExamId(Long examId);
}
