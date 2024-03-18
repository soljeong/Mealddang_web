package com.example.mealddang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mealddang.service.MdDietService;

@RestController
public class MdGraphRestController {

    @Autowired
    private MdDietService mdDietService;

    @PostMapping("/api-weekly")
    public Map<String, Object> getWeeklyReport(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        String username = authentication.getName();
        
        // 이번주 섭취량 요일별로 조회하기
        List<List<Object[]>> weekNutList = mdDietService.sumNutDaily(username);
        response.put("weekNutList", weekNutList);
        
        return response;
    }
}
