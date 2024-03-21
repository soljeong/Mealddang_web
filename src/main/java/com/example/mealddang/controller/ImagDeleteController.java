package com.example.mealddang.controller;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mealddang.service.MdImgService;

@Controller
public class ImagDeleteController {
    
    private final MdImgService mdImgService;

    public ImagDeleteController(MdImgService mdImgService) {
        this.mdImgService = mdImgService;
    }

    @GetMapping("/deleteAllResults")
    public String deleteAllResults(Authentication authentication) {
        String username = authentication.getName();
        mdImgService.deleteByUsername(username);
        
        return "redirect:/user/diet/log"; // 삭제 후 리다이렉트할 페이지 경로
    }

    @GetMapping("/deleteResults")
    public String deleteResultsByDate(Authentication authentication, @RequestParam("date") LocalDate selectedDate) {
        String username = authentication.getName();
        mdImgService.deleteByDate(username, selectedDate);

        return "redirect:/user/diet/log"; // 삭제 후 리다이렉트할 페이지 경로
    }
}
