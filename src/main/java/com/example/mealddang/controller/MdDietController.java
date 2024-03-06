package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.model.entity.MdGallery;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdGalleryService;
import com.example.mealddang.service.MdUserService;

// [인증 후] 식단관리 관련 컨트롤러
@Controller @RequestMapping("/user/diet")
public class MdDietController {
    @Autowired
    private MdUserService mdUserService;
    @Autowired
    private MdGalleryService mdGalleryService;
    
    @GetMapping("/log")
    public String getLog(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/logPage";
    }
    @GetMapping("/analysis")
    public String getImgAnalysis(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/analysisPage";
    }
    @PostMapping("/analysis/img")
    public String createGallery(
        @Validated @RequestParam("imgfile") MultipartFile imgfile, Model model, Authentication authentication) throws Exception {
            String username = authentication.getName();
            MdUser mdUser = mdUserService.findByUsername(username);
            model.addAttribute("mdUser", mdUser);

            MdGallery mdGallery = mdGalleryService.addMdGallery(MdGallery.builder().build(), imgfile);
            model.addAttribute("mdGallery", mdGallery);

            return "diet/analysisPage";
    }
    @GetMapping("/weekly")
    public String getWeeklyReport(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/weeklyPage";
    }

}
