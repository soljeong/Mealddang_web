package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdStoreService;
import com.example.mealddang.service.MdUserService;

// [인증 후] 맛집검색 관련 컨트롤러
@Controller @RequestMapping("/user/rsrt")
public class MdMapController {
    @Autowired
    private MdStoreService mdStoreService;
    @Autowired
    private MdUserService mdUserService;

    // 맛집검색 홈페이지
    @GetMapping("/map")
    public String getMap(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "map/navermap";
    }
    // 검색
    @GetMapping("/search")
    public String search(@RequestParam("searchkey") String searchkey, Model model) {
        if (ObjectUtils.isEmpty(searchkey)){
            return "redirect:/user/rsrt/map";
        }
        else {
            model.addAttribute("searchResults", mdStoreService.findAllByStoreName(searchkey));
            return "map/navermap";
        }
    }
}
