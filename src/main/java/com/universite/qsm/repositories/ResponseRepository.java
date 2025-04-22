package com.universite.qsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universite.qsm.entities.Response;




@Repository
public interface ResponseRepository extends JpaRepository< Response , Long > {
	
}
