package com.example.mealddang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MealddangApplication {
	public static void main(String[] args) {
		SpringApplication.run(MealddangApplication.class, args);
	}
}