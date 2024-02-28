package com.example.mealddang.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mealddang.model.entity.UserEntity;
import com.example.mealddang.service.UserService;

import jakarta.validation.Valid;


@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    // 회원가입 페이지 접속
    @GetMapping("/joinform")
    public String getJoinPage(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "joinForm";
    }

    // 회원가입 처리
    @PostMapping("/joinprocess")
    public String postJoinRequest(@Valid UserEntity userEntity, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패해도 입력한 데이터값을 유지합니다.
            model.addAttribute("userEntity", userEntity);
            // 실패 원인 메시지
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key: validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            // 회원가입 페이지로 리턴
            return "joinForm";
        }
        // 아이디 중복 검사
        userService.checkUsernameDuplication(userEntity);

        // 회원가입 성공
        userService.addUser(userEntity);
        
        return "redirect:/loginform";
    }
    
    // 로그인 페이지
    @GetMapping("/loginform")
    public String getLoginPage(Model model) {
        return "loginForm";
    }

}
