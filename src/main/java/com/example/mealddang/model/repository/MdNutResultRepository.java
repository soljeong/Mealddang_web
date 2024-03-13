package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdNutResult;

public interface MdNutResultRepository extends JpaRepository<MdNutResult, Long> {
    // 회원ID로 해당 회원의 모든 이미지분석 히스토리 찾기
    @Query(value = "SELECT * FROM md_nut_result WHERE img_path in(SELECT DISTINCT(img_path) FROM md_nut_result WHERE user_id = :username)", nativeQuery = true)
    public List<MdNutResult> findAllNutResultbyUsername(@Param(value="username") String username);
}
