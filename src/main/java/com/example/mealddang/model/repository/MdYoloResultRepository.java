package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdYoloResult;



public interface MdYoloResultRepository extends JpaRepository<MdYoloResult, Long> {
    // 회원ID로 해당 회원이 저장한 이미지업로드번호 모두 찾기
    @Query(value = "select distinct(upload_id) from md_yolo_result where user_id = :username", nativeQuery = true)
    public List<Long> findAllImgIDbyUserId(@Param(value="username") String username);
}