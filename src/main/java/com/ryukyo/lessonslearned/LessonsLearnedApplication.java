package com.ryukyo.lessonslearned;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.ryukyo.lessonslearned.repository")
@EntityScan("com.ryukyo.lessonslearned.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class LessonsLearnedApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessonsLearnedApplication.class, args);
	}

}
