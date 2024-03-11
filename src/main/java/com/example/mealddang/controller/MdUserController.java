package com.example.mealddang.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdImgService;
import com.example.mealddang.service.MdUserService;

import jakarta.validation.Valid;

// 회원 관련 서비스 컨트롤러
@Controller
public class MdUserController {

    @Autowired
    private MdUserService mdUserService;
    @Autowired
    private MdImgService mdImgService;

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
        return "user/loginform";
    }
    // 로그인 페이지
    @GetMapping("/loginform")
    public String getLoginPage(Model model) {
        return "user/loginForm";
    }
    // [인증 후] 메인 페이지
    @GetMapping("/user/main")
    public String getMain(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "user/mainPage";
    }
    // [인증 후] 마이 페이지
    @GetMapping("/user/mypage")
    public String getMyPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "user/myPage";
    }
    // [인증 후] 회원정보 수정
    // 1. 닉네임
    @PostMapping("/user/mypage/update_nickname")
    public String postNickname(@RequestParam String nickname, Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        mdUserService.modifyNickname(mdUser, nickname);
        model.addAttribute("mdUser", mdUser);
        return "redirect:/user/mypage";
    }
    // 2. 생일
    @PostMapping("/user/mypage/update_birth")
    public String postBirth(@RequestParam String birth, Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        mdUserService.modifyBirth(mdUser, birth);
        model.addAttribute("mdUser", mdUser);
        return "redirect:/user/mypage";
    }
    // 3. 성별
    @PostMapping("/user/mypage/update_gender")
    public String postGender(@RequestParam String gender, Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        mdUserService.modifyGender(mdUser, gender);
        model.addAttribute("mdUser", mdUser);
        return "redirect:/user/mypage";
    }
    // 4. 신장
    @PostMapping("/user/mypage/update_cm")
    public String postCm(@RequestParam String cm, Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        mdUserService.modifyCm(mdUser, cm);
        model.addAttribute("mdUser", mdUser);
        return "redirect:/user/mypage";
    }
    // 5. 몸무게
    @PostMapping("/user/mypage/update_kg")
    public String postKg(@RequestParam String kg, Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        mdUserService.modifyKg(mdUser, kg);
        model.addAttribute("mdUser", mdUser);
        return "redirect:/user/mypage";
    }
    // [인증 후] 마이 갤러리
    @GetMapping("user/mygallery")
    public String getMyGallery(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        List<MdImgUpload> mdImgUploads = mdImgService.findImgsByUserID(username);
        model.addAttribute("mdImgUploads", mdImgUploads);
        
        return "user/myGallery";
    }
}
