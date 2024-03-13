package com.example.mealddang.model.repository;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdKey;

public interface MdKeyRepository extends JpaRepository<MdKey,Long>{

    // @Query(value = "SELECT COUNT(DISTINCT restaurant_id) FROM md_restaurant_keyword WHERE keyword = :searchkey", nativeQuery = true)
    // int countByKeyword(@Param("searchkey") String searchkey);
    @Query(value = "SELECT DISTINCT rk.keyword_id, msm.store_id_n, msm.store_name, msm.store_phone, msm.store_road_address_n, msm.store_x, msm.store_y, msm.store_image_url, msm.store_menu_url_n " +
    "FROM md_restaurant_keyword rk JOIN md_store_master_tmp msm ON rk.restaurant_id = msm.store_id_n " +
    "WHERE keyword = :keyword", nativeQuery = true)
    public List<MdKey> findByKeyword(@Param(value="keyword") String keyword);
    
}
