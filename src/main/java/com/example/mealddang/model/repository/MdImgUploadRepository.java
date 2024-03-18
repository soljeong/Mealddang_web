package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdImgUpload;

public interface MdImgUploadRepository extends JpaRepository<MdImgUpload, String> {
    
    // ori_path_list로 모든 mdImgUpload 엔티티 찾기
    @Query(value = "select * from md_img_upload where origin_path in (:path_list)", nativeQuery = true)
    public List<MdImgUpload> findAllImgbyPaths(@Param(value = "path_list") List<String> path_list);

    // origin_path로 mdImgUpload 엔티티 찾기
    @Query(value = "select * from md_img_upload where origin_path = :ori_path", nativeQuery = true)
    public MdImgUpload findUploadEntitybyPath(@Param(value = "ori_path") String ori_path);
}