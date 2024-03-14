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

    // 회원ID로 해당 회원의 모든 업로드 이미지경로 찾기
    @Query(value = "SELECT DISTINCT(img_path) FROM md_nut_result WHERE user_id = :username", nativeQuery = true)
    public List<String> findAllPathbyUsername(@Param(value="username") String username);

    // 하루 섭취량 가져오기 (이번주 기준)
    @Query(value = "SELECT SUM(kcal), SUM(carbo_g), SUM(protein_g), SUM(fat_g) \n" + //
                "FROM md_nut_result \n" + //
                "WHERE user_id = :username \n" + //
                "AND created_date >= ADDDATE(CURDATE(), - WEEKDAY(CURDATE()) + :dayofweek)\n" + //
                "AND created_date < ADDDATE(CURDATE(), - WEEKDAY(CURDATE()) + :dayofweek + 1)", nativeQuery = true)
    public List<Float> sumNutDaily(@Param(value = "username") String username, @Param(value = "dayofweek") int dayofweek);

}
