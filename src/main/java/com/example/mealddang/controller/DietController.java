package com.example.mealddang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller @RequestMapping("/user/diet")
public class DietController {
    
    @GetMapping("/log")
    public String getLog(Model model) {
        return "diet/logPage";
    }
    @GetMapping("/analysis")
    public String getImgAnalysis(Model model) {
        return "diet/analysisPage";
    }
    @GetMapping("/weekly")
    public String getWeeklyReport(Model model) {
        return "diet/weeklyPage";
    }
    @GetMapping("/gallery")
    public String getGallery(Model model) {
        return "diet/gallery";
    }
}
