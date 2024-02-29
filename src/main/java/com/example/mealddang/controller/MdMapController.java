package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mealddang.service.MdStoreService;

// [인증 후] 맛집검색 관련 컨트롤러
@Controller @RequestMapping("/user/rsrt")
public class MdMapController {
    @Autowired
    private MdStoreService storeService;

    // 맛집검색 홈페이지
    @GetMapping("/map")
    public String getMap(Model model) {
        return "map/navermap";
    }
    // 검색
    @GetMapping("/search")
    public String search(@RequestParam("searchkey") String searchkey, Model model) {
        if (ObjectUtils.isEmpty(searchkey)){
            return "redirect:/user/rsrt/map";
        }
        else {
            model.addAttribute("searchResults", storeService.findAllByStoreName(searchkey));
            return "map/navermap";
        }
    }
}
