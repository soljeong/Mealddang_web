package com.example.mealddang.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdNutResult;
import com.example.mealddang.service.MdImgService;
import java.time.LocalDate;

@RestController
public class MdImgRestController {
    
    @Autowired
    private MdImgService mdImgService;

    // 글로벌변수
    private String originImgPath = "";
    private String username ="";

    // 이미지 업로드 메소드
    @PostMapping(value = {"/api-upload"}, consumes = {"multipart/form-data"})
    public ResponseEntity<Void> uploadImg(@Validated @RequestParam(value = "imgfile", required = false) MultipartFile imgfile, Authentication authentication, @RequestParam("date") LocalDate selectedDate) throws Exception {
        username = authentication.getName();
        // 서버로컬에 이미지 저장
        MdImgUpload mdImgUpload = mdImgService.imgUploader(MdImgUpload.builder().build(), username, imgfile, selectedDate);
        

        // 글로벌변수 originImgPath 업데이트
        originImgPath = mdImgUpload.getOriginPath();

        // View로 데이터 전달하지 않고 HTTP 상태 200 OK만 반환
        return ResponseEntity.ok().build();
    }

    // [미완] 욜로 모델로 원본 이미지 전달 및 결과 받는 메소드
    @GetMapping(value = {"/api-yolo"})
    public ResponseEntity<?> getYoloResult(@RequestParam("selectedDate") LocalDate selectedDate) {
        // 검출 API 요청
        List<String> yoloResultList = mdImgService.sendYolo(originImgPath);

        List<MdNutResult> mdNutResults = new ArrayList<>();


        // 검출 리스트가 1개 이상이면 DB 저장
        if (yoloResultList.size() != 0) {
            // API에 저장되어 있는 이미지 가져오기
            List<String> yoloImgeList = mdImgService.getYoloImg(yoloResultList, originImgPath);

            // 분석 결과 저장 (MdYoloResult roe n개 생성)
            mdImgService.saveYoloResult(originImgPath, yoloImgeList);
    
            // 분석 결과 저장2 (MDNutResult row n개 생성)
            mdNutResults = mdImgService.saveNutResult(username, originImgPath, yoloImgeList, selectedDate);
        }   
        else {
            // 검출 리스트가 0개이면 에러메시지 전달
            return ResponseEntity.badRequest().body("검출된 결과가 없습니다.");
        }

        // View로 데이터 전달 n개?
        Map<String, Object> response = new HashMap<>();

        response.put("mdNutResults", mdNutResults);
        response.put("listsize", mdNutResults.size());

        return ResponseEntity.ok().body(response);
    }

    // 이미지 삭제 (MdImgUpload, MdNutResult, MdYoloResult 모두?) : 보류
    @GetMapping("/api-delete")
    public void deleteImgUploadByOriPath() {
        mdImgService.deleteImgUploadByOriPath(originImgPath);
    }
}
