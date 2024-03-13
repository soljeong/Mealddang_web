package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdImgUpload;

public interface MdImgUploadRepository extends JpaRepository<MdImgUpload, String> {
    
    // 업로드이미지번호에 해당하는 업로드이미지엔티티 모두 찾기
    @Query(value = "select * from md_img_upload where img_path in (:path_list)", nativeQuery = true)
    public List<MdImgUpload> findAllImgsbyImgID(@Param(value = "path_list") List<String> path_list);
}