package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdUserService;


// [인증 후] 식단관리(밀땅일지) 컨트롤러
@Controller @RequestMapping("/user/diet")
public class MdDietController {

    @Autowired
    private MdUserService mdUserService;
    
    // 메인
    @GetMapping("/log")
    public String getLog(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
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
        return "diet/weeklyPage";
    }
}
