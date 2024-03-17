package com.example.mealddang.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdNutResult;
import com.example.mealddang.model.entity.MdUser;

public interface MdNutResultRepository extends JpaRepository<MdNutResult, Long> {
    // 회원ID로 해당 회원의 모든 이미지분석 히스토리 찾기
    @Query(value = "SELECT * FROM md_nut_result WHERE origin_path in(SELECT DISTINCT(origin_path) FROM md_nut_result WHERE user_id = :username)", nativeQuery = true)
    public List<MdNutResult> findAllNutResultbyUsername(@Param(value="username") MdUser mdUser);

    // 회원ID와 해당 주간의 모든 결과 가져오기
    @Query(value = "SELECT * FROM md_nut_result WHERE user_id = :username AND created_date BETWEEN :monday AND DATE_ADD(:monday, INTERVAL 6 DAY)", nativeQuery = true)
    public List<MdNutResult> findByMdUserAndDate(@Param(value="username") String username, @Param(value="monday") LocalDate monday);

    // 회원ID와 오늘 날짜의 모든 결과 가져오기
    @Query(value = "SELECT * FROM md_nut_result WHERE user_id = :username AND created_date = :today", nativeQuery = true)
    public List<MdNutResult> findByMdUserAndtoDate(@Param(value="username") String username, @Param(value="today") LocalDate today);



    // 회원ID로 해당 회원의 모든 업로드이미지(원본) 경로 찾기 -> MyGallery
    @Query(value = "SELECT DISTINCT(origin_path) FROM md_nut_result WHERE user_id = :username", nativeQuery = true)
    public List<String> findAllPathbyUsername(@Param(value="username") String username);

    // 하루 섭취량 가져오기 (이번주 기준) -> Weekly
    @Query(value = "SELECT  SUM(carbo_g), SUM(protein_g), SUM(fat_g) \n" + //
                "FROM md_nut_result \n" + //
                "WHERE user_id = :username \n" + //
                "AND created_date >= ADDDATE(CURDATE(), - WEEKDAY(CURDATE()) + :dayofweek)\n" + //
                "AND created_date < ADDDATE(CURDATE(), - WEEKDAY(CURDATE()) + :dayofweek + 1)", nativeQuery = true)
    public List<Object[]> sumNutDaily(@Param(value = "username") String username, @Param(value = "dayofweek") int dayofweek);

    


}

