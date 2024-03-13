package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.model.entity.MdYoloResult;
import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdImgService;
import com.example.mealddang.service.MdUserService;


// [인증 후] 식단관리(밀땅일지) 컨트롤러
@Controller @RequestMapping("/user/diet")
public class MdDietController {

    @Autowired
    private MdUserService mdUserService;
    @Autowired
    private MdImgService mdImgService;
    
    // 메인
    @GetMapping("/log")
    public String getLog(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/logPage";
    }

    // 상세1 이미지분석페이지
    @GetMapping("/analysis")
    public String getAnalysis(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/analysisPage";
    }
    // // 상세1 이미지분석페이지 업로드 메소드(이미지업로드 + 분석 + 저장 all in one)
    // @PostMapping("/analysis/upload")
    // public String uploadImg(
    //     @Validated @RequestParam("imgfile") MultipartFile imgfile, Model model, Authentication authentication) throws Exception {
    //         // UploadImg(업로드 이미지 원본) 저장
    //         MdImgUpload mdImgUpload = mdImgService.uploadImg(MdImgUpload.builder().build(), imgfile);
    //         model.addAttribute("mdImgUpload", mdImgUpload);

    //         // [미완]업로드 이미지 원본을 욜로모델에게 전달

    //         // YoloResult 저장 위해 유저아이디(YoloResult 속성) 가져오기
    //         String username = authentication.getName();
    //         MdUser mdUser = mdUserService.findByUsername(username);
    //         model.addAttribute("mdUser", mdUser);

    //         // YoloResult(분석 결과) 저장
    //         // 아래 2줄은 개발용 가짜 정보임, DB 연결 완성되면 수정할 것.
    //         String resultName = "김치";

    //         MdYoloResult mdYoloResult = mdImgService.saveYoloResult(mdImgUpload, resultName);
    //         model.addAttribute("mdYoloResult", mdYoloResult);

    //         return "diet/analysisPage";
    // }

    // [미완] 상세1 이미지분석페이지 분석결과확인 메소드
    // @GetMapping("/analysis/result")
    // public String yoloResult(Model model) {
    //     return "diet/analysisPage";
    // }
    
    // 밀땅일지 - 주간레포트
    @GetMapping("/weekly")
    public String getWeeklyReport(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/weeklyPage";
    }
}
