package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdImgUpload;

public interface MdImgUploadRepository extends JpaRepository<MdImgUpload,Long> {
    
    // 업로드이미지번호에 해당하는 업로드이미지엔티티 모두 찾기
    @Query(value = "select * from md_img_upload where upload_id in (:id_list)", nativeQuery = true)
    public List<MdImgUpload> findImgsByUpIDs(@Param(value = "id_list") List<Long> id_list);
}
