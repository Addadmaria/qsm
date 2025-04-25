package com.universite.qsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
    scanBasePackages = "com.universite.qsm"  
)
@EntityScan(basePackages = "com.universite.qsm.entities")        
@EnableJpaRepositories(basePackages = "com.universite.qsm.repositories")
public class QsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(QsmApplication.class, args);
	}

}
