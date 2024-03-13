package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdImgUpload;

public interface MdImgUploadRepository extends JpaRepository<MdImgUpload, String> {
    
    // img_path_list로 모든 업로드이미지원본 엔티티 찾기
    @Query(value = "select * from md_img_upload where img_path in (:path_list)", nativeQuery = true)
    public List<MdImgUpload> findAllImgbyPaths(@Param(value = "path_list") List<String> path_list);
}