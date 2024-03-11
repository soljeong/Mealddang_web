package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.model.entity.MdImgIsolated;
import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdImgService;
import com.example.mealddang.service.MdUserService;


// [인증 후] 식단관리 관련 컨트롤러
@Controller @RequestMapping("/user/diet")
public class MdDietController {

    @Autowired
    private MdUserService mdUserService;
    @Autowired
    private MdImgService mdImgService;
    
    // 밀땅일지
    @GetMapping("/log")
    public String getLog(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/logPage";
    }

    // 밀땅일지 - 이미지분석페이지
    @GetMapping("/analysis")
    public String getAnalysis(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/analysisPage";
    }
    // 밀땅일지 - 이미지분석페이지 - 이미지업로드
    @PostMapping("/analysis/upload")
    public String uploadImg(
        @Validated @RequestParam("imgfile") MultipartFile imgfile, Model model, Authentication authentication) throws Exception {
            String username = authentication.getName();
            MdUser mdUser = mdUserService.findByUsername(username);
            model.addAttribute("mdUser", mdUser);
            MdImgUpload mdImgUpload = mdImgService.uploadImg(MdImgUpload.builder().build(), imgfile);
            model.addAttribute("mdImgUpload", mdImgUpload);
            return "diet/analysisPage";
    }
    // 밀땅일지 - 이미지분석페이지 - AI분석& 저장
    @GetMapping("/analysis/yolo-start")
    public String yoloServing(@ModelAttribute MdImgUpload imgUpload, Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        String isoResult = "김치";
        String isoStored = "static/isolated/20210308/kimchi.jpg";
        MdImgIsolated mdImgIsolated = mdImgService.saveIso(mdUser, imgUpload, isoResult, isoStored);

        model.addAttribute("mdImgIsolated", mdImgIsolated);

        return "diet/analysisPage";
    }
    
    // 밀땅일지 - 주간레포트
    @GetMapping("/weekly")
    public String getWeeklyReport(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        return "diet/weeklyPage";
    }

}
