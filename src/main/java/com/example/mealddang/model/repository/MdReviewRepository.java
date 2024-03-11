package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdReview;

public interface MdReviewRepository extends JpaRepository<MdReview,String> {
    // 리뷰 검색 쿼리
    @Query(value = "SELECT store_id_o, review \n" + //
            "FROM md_review \n" + //
            "WHERE review LIKE CONCAT('%', :searchkey, '%')", nativeQuery = true)
    public List<MdReview> findReviewsByReview(@Param(value="searchkey") String searchkey);
}
