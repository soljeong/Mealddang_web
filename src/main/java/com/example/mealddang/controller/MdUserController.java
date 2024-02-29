package com.example.mealddang.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdUserService;

import jakarta.validation.Valid;

// 회원 관련 서비스 컨트롤러
@Controller
public class MdUserController {
    @Autowired
    MdUserService mdUserService;
    // 테스트 페이지
    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }
    // 회원가입 페이지
    @GetMapping("/joinform")
    public String getJoinPage(Model model) {
        model.addAttribute("mdUser", new MdUser());
        return "user/joinForm";
    }
    // 회원가입 처리
    @PostMapping("/joinprocess")
    public String postJoinRequest(@Valid MdUser mdUser, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패해도 입력한 데이터값을 유지
            model.addAttribute("mdUser", mdUser);
            // 실패 원인 메시지
            Map<String, String> validatorResult = mdUserService.validateHandling(errors);
            for (String key: validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            // 회원가입 페이지로 리턴
            return "user/joinForm";
        }
        // 아이디 중복 검사
        mdUserService.checkUsernameDuplication(mdUser);
        // 회원 저장
        mdUserService.addMdUser(mdUser);
        return "redirect:/loginform";
    }
    // 로그인 페이지
    @GetMapping("/loginform")
    public String getLoginPage(Model model) {
        return "user/loginForm";
    }
    // [인증 후] 메인 페이지
    @GetMapping("/user/main")
    public String getMain(Model model) {
        return "user/mainPage";
    }
    // [인증 후] 마이 페이지
    @GetMapping("/user/mypage")
    public String getMyPage(Model model) {
        return "user/myPage";
    }
}
