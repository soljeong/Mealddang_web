package com.example.mealddang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mealddang.service.ImagDeleteService;

@Controller
public class ImagDeleteController {
    
    private final ImagDeleteService imagDeleteService;

    public ImagDeleteController(ImagDeleteService imagDeleteService) {
        this.imagDeleteService = imagDeleteService;
    }

    @GetMapping("/deleteAllResults")
    public String deleteAllResults() {
        
        imagDeleteService.deleteAllResults();
        
        return "redirect:/user/diet/log"; // 삭제 후 리다이렉트할 페이지 경로
    }
}
