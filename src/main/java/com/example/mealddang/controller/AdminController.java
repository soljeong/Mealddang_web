package com.example.mealddang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // 관리자 홈페이지
    @GetMapping("/admin/home")
    public String admin() {
        return "admin/adminHome";
    }
}
