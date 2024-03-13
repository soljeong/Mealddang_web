package com.example.mealddang.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.service.MdImgService;
import com.example.mealddang.service.MdUserService;

@RestController
public class MdImgRestController {
    
    @Autowired
    private MdImgService mdImgService;
    @Autowired
    private MdUserService mdUserService;

    // 이미지 업로드 메소드
    @PostMapping(value = {"/api-upload"}, consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadImg(@Validated @RequestParam(value = "imgfile", required = false) MultipartFile imgfile, Authentication authentication) throws Exception {

        // 업로드 이미지 저장 (MdImgUpload 엔티티 생성)
        MdImgUpload mdImgUpload = mdImgService.uploadImg(MdImgUpload.builder().build(), imgfile);

        // [미완]업로드 이미지 원본을 욜로모델에게 전달

        // 모델분석 결과 저장 (MdYoloResult 엔티티 생성)
        String label = "김치";        // 개발용 가짜 정보임, DB 연결 완성되면 수정할 것.
        String imgPath = "/";         // 개발용 가짜 정보임, DB 연결 완성되면 수정할 것.
        mdImgService.saveYoloResult(imgPath, label);

        // 식단분석 결과 저장 (MDNutResult 엔티티 생성)
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        mdImgService.saveNutResult(mdUser, mdImgUpload);

        Map<String, Object> response = new HashMap<>();
        response.put("imgUrl", mdImgUpload.getImgPath());
        return ResponseEntity.ok().body(response);
    }

}
