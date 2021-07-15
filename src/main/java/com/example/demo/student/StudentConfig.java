package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student Thamer = new Student(
				"Thamer",
				"Thamer@gmail.com",
				LocalDate.of(2002, Month.JULY, 22)
			);
			Student Aziz = new Student(
				"Aziz",
				"Aziz@gmail.com",
				LocalDate.of(2006, Month.JULY, 2)
			);
			
			repository.saveAll(
					List.of(Thamer, Aziz)
				);
		};
	}

}