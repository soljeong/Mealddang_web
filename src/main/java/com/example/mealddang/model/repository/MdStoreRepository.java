package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdStore;

public interface MdStoreRepository extends JpaRepository<MdStore, Integer> {
    // 식당 리스트 검색 쿼리
    @Query(value = "select * from restaurants_yogiyo where name like CONCAT('%', :searchkey, '%')", nativeQuery = true)
    public List<MdStore> findAllByStoreName(@Param(value="searchkey") String searchkey);

}
