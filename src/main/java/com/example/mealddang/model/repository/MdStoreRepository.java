package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdStore;

public interface MdStoreRepository extends JpaRepository<MdStore, String> {
    // 식당 이름 검색 쿼리
    @Query(value = "select store_id_n,상호명,phone,roadaddress,경도,위도 from md_store_master where 상호명 like CONCAT('%', :searchkey, '%')", nativeQuery = true)
    public List<MdStore> findAllByStoreName(@Param(value="searchkey") String searchkey);

    // // 리뷰에 해당하는 식당 검색 쿼리..
    // @Query(value = "SELECT shopname, shopaddress FROM restaurants_catchtable WHERE shopname IN (SELECT shop_name FROM reviews_catchtable WHERE mask_review_content LIKE CONCAT('%', :searchkey, '%');", nativeQuery = true)
    // public List<MdStore> findStoresByReview(@Param(value = "searchkey") String searchkey);
}
