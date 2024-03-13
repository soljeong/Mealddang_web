package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdNutResult;

public interface MdNutResultRepository extends JpaRepository<MdNutResult, Long> {
    // 회원ID로 해당 회원이 업로드한 img_path 모두 찾기
    @Query(value = "select distinct(img_path) from md_nut_result where user_id = :username", nativeQuery = true)
    public List<String> findAllPathbyUserId(@Param(value="username") String username);
}
