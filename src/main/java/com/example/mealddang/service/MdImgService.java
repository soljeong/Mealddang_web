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

@Service @Slf4j
public class MdImgService {

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
    public MdImgUpload uploadImg(MdImgUpload p_upload, MultipartFile imgFile) throws Exception {
        MdImgUpload mdImgUpload = fileHandler.parseFileInfo(p_upload.getImgPath(), imgFile);
        mdImgUploadRepository.save(mdImgUpload);
        return mdImgUpload;
    }

    // MdNutResult 초기화: 이미지분석결과 나오기 전 회원ID와 이미지경로 선 저장
    public MdNutResult saveNutResult(MdUser p_username, MdImgUpload p_imgpath) {
        MdNutResult mdNutResult = new MdNutResult();
        mdNutResult.setUsername(p_username);
        mdNutResult.setImgPath(p_imgpath);
        mdNutResultRepository.save(mdNutResult);
        return mdNutResult;
    }

    // MdYoloResult 저장
    public MdYoloResult saveYoloResult(String imgPath, String label) {
        MdYoloResult mdYoloResult = new MdYoloResult();
        mdYoloResult.setImgPath(imgPath);
        mdYoloResult.setResultLabel(label);
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

}
