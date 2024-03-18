package com.example.mealddang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mealddang.service.MdDietService;

@RestController
public class MdGraphRestController {

    @Autowired
    private MdDietService mdDietService;

    @GetMapping("/api-weekly")
    public Map<String, Object> getWeeklyReport(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        String username = authentication.getName();
        
        // 이번주 섭취량 요일별로 조회하기
        List<List<Float>> weekNutList = mdDietService.getWeeklySumNutrition(username);
        response.put("weekNutList", weekNutList);

        // 일주일치 통합(월+화+수+...+일) -> [7일치 kcal, 7일치 carb, 7일치 prot, 7일치 fat]
        List<Float> weekTotal = mdDietService.weekTotal(username);
        response.put("weekTotal", weekTotal);
        
        return response;
    }
}
