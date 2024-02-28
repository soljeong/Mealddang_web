package com.example.mealddang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/mainpage")
public class MapController {
    
    @GetMapping("/map")
    public String getMap(Model model) {
        return "/navermap";
    }
    
    @GetMapping("/mapmarker")
	public String naverMap() {
		return "/navermarker";
	}


}
