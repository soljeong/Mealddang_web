package com.example.mealddang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mealddang.model.entity.MdDiet;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdDietService;
import com.example.mealddang.service.MdUserService;
import org.springframework.web.bind.annotation.RequestParam;


// [인증 후] 식단관리(밀땅일지) 컨트롤러
@Controller @RequestMapping("/user/diet")
public class MdDietController {

    @Autowired
    private MdUserService mdUserService;
    @Autowired
    private MdDietService mdDietService;
    
    // 메인
    @GetMapping("/log")
    public String getLog(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        // 유저 나이와 성별에 맞는 섭취기준(MdDiet엔티티) 불러오기
        MdDiet md_diet = mdDietService.getDiet(mdUser);
        model.addAttribute("md_diet", md_diet);
        
        return "diet/logPage";
    }

    // 상세1 이미지분석페이지
    @GetMapping("/analysis")
    public String getAnalysis(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
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

        // // 이번주 섭취량 요일별로 조회하기
        // List<List<Object[]>> weekNutList = mdDietService.sumNutDaily(username);
        // model.addAttribute("weekNutList", weekNutList);

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
}
