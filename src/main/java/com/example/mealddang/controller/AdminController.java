package com.example.mealddang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// [인증, 인가 후] 관리자용 컨트롤러
@Controller @RequestMapping("/admin")
public class AdminController {
    @GetMapping("/home")
    public String admin() {
        return "admin/adminHome";
    }
}
