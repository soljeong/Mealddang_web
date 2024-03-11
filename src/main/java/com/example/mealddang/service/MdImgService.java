package com.example.mealddang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.config.handler.FileHandler;
import com.example.mealddang.model.entity.MdImgIsolated;
import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdImgIsolatedRepository;
import com.example.mealddang.model.repository.MdImgUploadRepository;

@Service
public class MdImgService {

    @Autowired
    private MdImgUploadRepository mdImgUploadRepository;

    @Autowired
    private MdImgIsolatedRepository mdImgIsolatedRepository;

    private final FileHandler fileHandler;
    
    // 생성자
    @Autowired
    public MdImgService(MdImgUploadRepository mdImgUploadRepository) {
        this.mdImgUploadRepository = mdImgUploadRepository;
        this.fileHandler = new FileHandler();
    }

    // 이미지업로더
    public MdImgUpload uploadImg(MdImgUpload p_upload, MultipartFile imgFile) throws Exception {
        MdImgUpload mdImgUpload = fileHandler.parseFileInfo(p_upload.getUploadId(), imgFile);
        mdImgUploadRepository.save(mdImgUpload);
        return mdImgUpload;
    }

    // 이미지분석정보 저장
    public MdImgIsolated saveIso(MdUser username, MdImgUpload uploadId, String isoResult, String isoStored) {
        MdImgIsolated mdImgIsolated = new MdImgIsolated();
        mdImgIsolated.setUsername(username);
        mdImgIsolated.setUploadId(uploadId);
        mdImgIsolated.setIsoResult(isoResult);
        mdImgIsolated.setIsoStored(isoStored);

        mdImgIsolatedRepository.save(mdImgIsolated);
        return mdImgIsolated;
    }

    // 회원ID로 해당 회원이 업로드한 모든 이미지 찾기
    public List<MdImgUpload> findImgsByUserID(String username) {
        List<Long> mdUpIDs = mdImgIsolatedRepository.findUpIDsByUserID(username);
        return mdImgUploadRepository.findImgsByUpIDs(mdUpIDs);
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
