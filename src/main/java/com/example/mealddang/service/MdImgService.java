package com.example.mealddang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.config.handler.FileHandler;
import com.example.mealddang.model.entity.MdYoloResult;
import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdYoloResultRepository;

import lombok.extern.slf4j.Slf4j;

import com.example.mealddang.model.repository.MdImgUploadRepository;

@Service @Slf4j
public class MdImgService {

    @Autowired
    private MdImgUploadRepository mdImgUploadRepository;

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
        MdImgUpload mdImgUpload = fileHandler.parseFileInfo(p_upload.getUploadId(), imgFile);
        mdImgUploadRepository.save(mdImgUpload);
        return mdImgUpload;
    }

    // 이미지분석정보 저장
    public MdYoloResult saveYoloResult(MdUser username, MdImgUpload upload, String resultName, String resultStored) {
        MdYoloResult mdYoloResult = new MdYoloResult();
        mdYoloResult.setUsername(username);
        mdYoloResult.setUploadId(upload);
        mdYoloResult.setResultName(resultName);
        mdYoloResult.setResultStored(resultStored);

        mdYoloResultRepository.save(mdYoloResult);
        return mdYoloResult;
    }

    // 회원ID로 해당 회원이 업로드한 모든 이미지 찾기
    public List<MdImgUpload> findAllImgbyUsername(String username) {
        log.info(username + " 회원님의 갤러리를 찾고 있습니다.");
        List<Long> imgIDs = mdYoloResultRepository.findAllImgIDbyUserId(username);
        log.info(imgIDs.size() + "개의 이미지를 찾았습니다.");
        return mdImgUploadRepository.findAllImgsbyImgID(imgIDs);
    }
    
    // // 올린 이미지 모두 찾기
    // public List<MdImgUpload> findAllImgUpload() {
    //     return mdImgUploadRepository.findAll();
    // }

    // // 올린 이미지 1개 PK로 찾기
    // public Optional<MdImgUpload> findImgUpload(Long id) {
    //     return mdImgUploadRepository.findById(id);
    // }
}
