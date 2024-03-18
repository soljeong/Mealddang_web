package com.example.mealddang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mealddang.model.repository.MdNutResultRepository;
import com.example.mealddang.service.MdDietService;

@SpringBootTest
class MealddangApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	MdNutResultRepository mdNutResultRepository;
	@Test
	void test() {

		System.out.println(mdNutResultRepository.sumNutDaily("test", 4));


	}

	@Autowired
	MdDietService mdDietService;
	
	// getWeeklySumNutrition test
	@Test
	void getWeeklySumNutritionTest() {
		System.out.println(mdDietService.getWeeklySumNutrition("test"));
	}


}
