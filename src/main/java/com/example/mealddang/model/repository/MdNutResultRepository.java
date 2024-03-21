package com.example.mealddang.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    @Query(value = "SELECT ROUND(SUM(carbo_g),0), ROUND(SUM(protein_g),0), ROUND(SUM(fat_g),0) \n" + //
                "FROM md_nut_result \n" + //
                "WHERE user_id = :username \n" + //
                "AND created_date >= ADDDATE(CURDATE(), - WEEKDAY(CURDATE()) + :dayofweek)\n" + //
                "AND created_date < ADDDATE(CURDATE(), - WEEKDAY(CURDATE()) + :dayofweek + 1)", nativeQuery = true)
    public List<Object[]> sumNutDaily(@Param(value = "username") String username, @Param(value = "dayofweek") int dayofweek);

    // 일주일치 통합(월+화+수+...+일) 섭취량 -> Weekly
    @Query(value = "SELECT ROUND(SUM(m.kcal),0), ROUND(SUM(m.carbo_g),0), ROUND(SUM(m.protein_g),0), ROUND(SUM(m.fat_g),0)" +
                "FROM md_nut_result m " +
                "WHERE m.user_id = :username " +
                "AND m.created_date >= ADDDATE(CURDATE(), - WEEKDAY(CURDATE()))" + 
                "AND m.created_date < ADDDATE(CURDATE(), - WEEKDAY(CURDATE())+7)", nativeQuery = true)
    public List<Object[]> weekTotal(@Param(value = "username") String username);

    // 해당 회원의 가장 최근 결과 가져오기 [삭제 예정]
    @Query(value = "SELECT * FROM md_nut_result WHERE user_id = :username ORDER BY update_date DESC LIMIT 1", nativeQuery = true)
    public MdNutResult findTop1ByMdUserOrderByUpdateDateDesc(@Param(value = "username") String username);

    // origin_path로 분석결과 리스트 가져오기
    @Query(value = "SELECT * FROM md_nut_result WHERE user_id = :username AND origin_path = :origin_path", nativeQuery = true)
    public List<MdNutResult> findAllNResultByOriPath(@Param(value = "username") String username, @Param(value = "origin_path") String origin_path);

    // 해당 유저의 모든 Nut Result 삭제하기
    @Modifying @Transactional
    @Query(value = "DELETE FROM md_nut_result WHERE user_id = :username", nativeQuery = true)
    public void deleteByUsername(@Param(value = "username") String username);

    // 해당 유저의 하루치 Nut Result 삭제하기
    @Modifying @Transactional
    @Query(value = "DELETE FROM md_nut_result WHERE user_id = :username AND created_date = :select_date", nativeQuery = true)
    public void deleteByDate(@Param(value = "username") String username, @Param(value = "select_date") LocalDate selectedDate);
}

