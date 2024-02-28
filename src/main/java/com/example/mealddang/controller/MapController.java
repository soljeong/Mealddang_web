package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mealddang.service.StoreService;

@Controller @RequestMapping("/user/rsrt")
public class MapController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/map")
    public String userMap(Model model) {
        return "map/navermap";
    }
    
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
