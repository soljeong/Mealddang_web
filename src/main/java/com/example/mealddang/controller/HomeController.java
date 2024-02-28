package com.example.mealddang.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mealddang.model.entity.StoreEntity;
import com.example.mealddang.service.StoreService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @Autowired
    private StoreService storeService;

    // 회원 홈페이지
    @GetMapping("/user/home")
    public String userHome(Model model) {
        return "user/userHome";
    }

    // 메인페이지 (map)
    @GetMapping("/user/map")
    public String userMap(Model model) {
        return "user/userMap";
    }
    
    @GetMapping("/user/map/search")
    public String search(@RequestParam("searchkey") String searchkey, Model model) {
        if (ObjectUtils.isEmpty(searchkey)){
            return "redirect:/user/map";
        }
        else {
            model.addAttribute("searchResults", storeService.findAllByStoreName(searchkey));
            return "user/userMap";
        }
    }
    
    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    // 관리자 홈페이지
    @GetMapping("/admin/home")
    public String admin() {
        return "admin/adminHome";
    }
}
