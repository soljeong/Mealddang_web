package com.example.mealddang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mealddang.model.entity.MdDiet;
import com.example.mealddang.model.entity.MdNutResult;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdNutResultRepository;
import com.example.mealddang.service.MdDietService;
import com.example.mealddang.service.MdUserService;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// [인증 후] 식단관리(밀땅일지) 컨트롤러
@Controller @RequestMapping("/user/diet")
public class MdDietController {
    private static final Logger logger = LoggerFactory.getLogger(MdDietController.class);

    @Autowired
    private MdUserService mdUserService;
    @Autowired
    private MdDietService mdDietService;
    @Autowired
    private MdNutResultRepository mdNutResultRepository;

    // 메인
    @GetMapping("/log")
    public String getLog(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        // 유저 나이와 성별에 맞는 섭취기준(MdDiet엔티티) 불러오기
        MdDiet md_diet = mdDietService.getDiet(mdUser);
        model.addAttribute("md_diet", md_diet);

        // 월요일 가져옴
        LocalDate monday = mdDietService.getWeekDatesFromMonday(); 
        model.addAttribute("monday", monday);

        // 오늘 날짜
        LocalDate today = mdDietService.getWeekDatesFromtoday(); 
        model.addAttribute("today", today);

        List<MdNutResult> results = mdNutResultRepository.findByMdUserAndDate(username, monday);
        model.addAttribute("results", results);
        
        // List<MdNutResult> nutResults = mdNutResultRepository.findByMdUserAndDate(mdUser, selectedDate);
        // logger.info("Nutrition results for user {} on date {}: {}", mdUser.getUsername(), selectedDate, nutResults);

        // model.addAttribute("mdUser", mdUser);
        // model.addAttribute("selectedDate", selectedDate);
        // if (!nutResults.isEmpty()) { 
        //     MdNutResult firstResult = nutResults.get(0); // 첫 번째 결과만 가져옴
        //     model.addAttribute("nutResults", nutResults); // 모든 결과를 추가할 수도 있음
        
        //     // 영양성분 값 추가
        //     model.addAttribute("kcal", firstResult.getKcal() != null ? firstResult.getKcal() : 0);
        //     model.addAttribute("carbog", firstResult.getCarboG() != null ? firstResult.getCarboG() : 0);
        //     model.addAttribute("fatg", firstResult.getFatG() != null ? firstResult.getFatG() : 0);
        //     model.addAttribute("proteing", firstResult.getProteinG() != null ? firstResult.getProteinG() : 0);
        // } else {
        //     // 이미지 분석 결과가 없는 경우에도 0으로 초기화하여 모델에 추가
        //     model.addAttribute("kcal", 0);
        //     model.addAttribute("carbog", 0);
        //     model.addAttribute("fatg", 0);
        //     model.addAttribute("proteing", 0);
        // }

        return "diet/logPage";
    }

    // 상세1 이미지분석페이지
    @GetMapping("/analysis")
    public String getAnalysis(Model model, Authentication authentication, @RequestParam("date") LocalDate selectedDate) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        model.addAttribute("selectedDate", selectedDate);

        // 현재 유저 ID에 해당하는 md_nut_result 데이터 가져오기
        // List<MdNutResult> userResults = mdNutResultRepository.findAllNutResultbyUsername(mdUser);
        // 현재 유저 ID와 선택된 날짜에 해당하는 md_nut_result 데이터 가져오기
        
        // model.addAttribute("nutResults", nutResults);

        // // md_nut_result에서 가져온 데이터를 기반으로 영양성분 값 가져오기
        // for (MdNutResult nutResult : nutResults) {
        //     model.addAttribute("resultLabel", nutResult.getResultLabel());
        //     model.addAttribute("foodName", nutResult.getResultLabel());
        //     model.addAttribute("kcal", nutResult.getKcal());
        //     model.addAttribute("carbog", nutResult.getCarboG());
        //     model.addAttribute("fatg", nutResult.getFatG());
        //     model.addAttribute("proteing", nutResult.getProteinG());
        // }
        
        // 밑에서 부턴 추가 내용

        // // 현재 유저 ID와 선택된 날짜에 해당하는 md_nut_result 데이터 가져오기
        // List<MdNutResult> nutResults = mdNutResultService.findByUserIdAndDate(mdUser.getId(), selectedDate);

        // // md_nut_result에서 가져온 데이터를 기반으로 영양성분 값 가져오기
        // for (MdNutResult nutResult : nutResults) {
        //     String resultLabel = nutResult.getResultLabel();
        //     String foodName = nutResult.getFoodName();
            
        //     // md_nut_info에서 result_label과 food_name이 일치하는 데이터 가져오기
        //     MdNutInfo nutInfo = mdNutInfoService.findByResultLabelAndFoodName(resultLabel, foodName);
            
        //     // 해당하는 영양성분 값을 모델에 추가
        //     model.addAttribute(resultLabel, nutInfo);
        // }
        return "diet/analysisPage";
    }

    // [미완] 상세1 이미지분석페이지 분석결과확인 메소드
    // @GetMapping("/analysis/result")
    // public String yoloResult(Model model) {
    //     return "diet/analysisPage";
    // }
    
    // 밀땅일지 - 주간레포트
    @GetMapping("/weekly")
    public String getWeeklyReport(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        // 유저 나이와 성별에 맞는 섭취기준(MdDiet엔티티) 불러오기
        MdDiet md_diet = mdDietService.getDiet(mdUser);
        model.addAttribute("md_diet", md_diet);

        // 유저의 일주일치 섭취량 불러오기
        List<Float> user_diet = mdDietService.weekTotal(username);
        model.addAttribute("user_diet", user_diet);

        return "diet/weeklyPage";
    }

    // 이미지분석 성공 결과 user/diet/analysis_result_ok
    @GetMapping("/analysis_result_ok")
    public String getAnalysisResult(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        return "diet/resultPage";
    }
    // 이미지분석 실패 결과  user/diet/analysis_result_fail
    @GetMapping("/analysis_result_fail")
    public String getFailResult(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        
        return "diet/failPage";
    }


    @GetMapping("/user/diet/nutrition")
    public List<Object[]> getNutritionData(@RequestParam("username") String username, @RequestParam("dayofweek") int dayofweek) {
        return mdNutResultRepository.sumNutDaily(username, dayofweek);
    }
}
