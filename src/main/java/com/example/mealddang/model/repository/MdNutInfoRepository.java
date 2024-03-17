package com.example.mealddang.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mealddang.model.entity.MdNutInfo;

public interface MdNutInfoRepository extends JpaRepository<MdNutInfo, String>{
    // 결과 라벨로 음식명 찾기
    @Query(value = "SELECT * FROM md_nut_info WHERE class_label = :label LIMIT 1", nativeQuery = true)
    public MdNutInfo findByClassLabel(String label);
}
