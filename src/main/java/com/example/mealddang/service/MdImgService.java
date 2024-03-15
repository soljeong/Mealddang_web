package com.example.mealddang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.config.handler.FileHandler;
import com.example.mealddang.model.entity.MdYoloResult;
import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdNutResult;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdYoloResultRepository;

import lombok.extern.slf4j.Slf4j;

import com.example.mealddang.model.repository.MdImgUploadRepository;
import com.example.mealddang.model.repository.MdNutResultRepository;
import com.example.mealddang.model.repository.MdUserRepository;

@Service @Slf4j
public class MdImgService {

    @Autowired
    private MdUserRepository mdUserRepository;
    @Autowired
    private MdImgUploadRepository mdImgUploadRepository;
    @Autowired
    private MdNutResultRepository mdNutResultRepository;
    @Autowired
    private MdYoloResultRepository mdYoloResultRepository;

    private final FileHandler fileHandler;
    
    // 생성자
    @Autowired
    public MdImgService(MdImgUploadRepository p_uploadRepository) {
        this.mdImgUploadRepository = p_uploadRepository;
        this.fileHandler = new FileHandler();
    }

    // 이미지업로더
    public MdImgUpload imgUploader(MdImgUpload p_upload, String p_username,  MultipartFile imgFile) throws Exception {
        MdImgUpload mdImgUpload = fileHandler.parseFileInfo(p_upload.getOriginPath(), p_username, imgFile);
        // DB에 저장
        mdImgUploadRepository.save(mdImgUpload);
        return mdImgUpload;
    }

    // MdNutResult 저장
    public MdNutResult saveNutResult(String p_username, String p_oripath, String p_resultpath, String p_resultlabel) {
        MdUser mdUser = mdUserRepository.findByUsername(p_username).get();
        MdImgUpload mdImgUpload = mdImgUploadRepository.findUploadEntitybyPath(p_oripath);
        
        MdNutResult mdNutResult = new MdNutResult();
        mdNutResult.setUsername(mdUser);
        mdNutResult.setOriginPath(mdImgUpload);
        mdNutResult.setResultPath(p_resultpath);
        mdNutResult.setResultLabel(p_resultlabel);

        mdNutResultRepository.save(mdNutResult);

        return mdNutResult;
    }

    // MdYoloResult 저장
    public MdYoloResult saveYoloResult(String p_originPath, String p_resultPath) {
        MdYoloResult mdYoloResult = new MdYoloResult();
        mdYoloResult.setOriginPath(p_originPath);
        mdYoloResult.setResultPath(p_resultPath);
        mdYoloResultRepository.save(mdYoloResult);
        return mdYoloResult;
    }

    // 회원ID로 해당 회원의 모든 업로드이미지원본 엔티티 찾기
    public List<MdImgUpload> findAllUploadbyUsername(String username) {
        log.info(username + " 회원님의 모든 업로드 히스토리를 찾고 있습니다.");
        List<String> imgPaths = mdNutResultRepository.findAllPathbyUsername(username);
        log.info(imgPaths.size() + "개의 업로드 히스토리를 찾았습니다.");
        return mdImgUploadRepository.findAllImgbyPaths(imgPaths);
    }

    // 회원ID로 해당 회원의 모든 이미지분석결과 엔티티 찾기
    public List<MdNutResult> findAllNutResultbyUsername(String username) {
        log.info(username + " 회원님의 모든 이미지 분석 히스토리를 찾고 있습니다.");
        List<MdNutResult> mdNutResults = mdNutResultRepository.findAllNutResultbyUsername(username);
        log.info(mdNutResults.size() + "개의 이미지 분석 히스토리를 찾았습니다.");
        return mdNutResults;
    }

    // originPath로 이미지 삭제하기_보류
}
