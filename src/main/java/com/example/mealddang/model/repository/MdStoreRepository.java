package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdStore;

public interface MdStoreRepository extends JpaRepository<MdStore, String> {
    // 식당 이름 검색 쿼리
    @Query(value = "select store_id_n,store_name,store_phone,store_road_address_n,store_x,store_y,store_image_url,store_menu_url_n from md_store_master where store_name like CONCAT('%', :searchkey, '%')", nativeQuery = true)
    public List<MdStore> findAllByStoreName(@Param(value="searchkey") String searchkey);

    // // 리뷰에 해당하는 식당 검색 쿼리..
    // @Query(value = "SELECT shopname, shopaddress FROM restaurants_catchtable WHERE shopname IN (SELECT shop_name FROM reviews_catchtable WHERE mask_review_content LIKE CONCAT('%', :searchkey, '%');", nativeQuery = true)
    // public List<MdStore> findStoresByReview(@Param(value = "searchkey") String searchkey);
}
