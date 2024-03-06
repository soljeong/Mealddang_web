package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdReview;

public interface MdReviewRepository extends JpaRepository<MdReview,Long> {
    // 리뷰 검색 쿼리
    @Query(value = "select review_id,restaurant_id,shop_name,review_content from reviews_catchtable where 1=1 and mask_review_content like CONCAT('%', :searchkey, '%')", nativeQuery = true)
    public List<MdReview> findReviewsByReview(@Param(value="searchkey") String searchkey);
}
