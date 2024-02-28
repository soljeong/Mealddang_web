package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {
    @Query(value = "select * from restaurants_naver where name like CONCAT('%', :searchkey, '%')", nativeQuery = true)
    public List<StoreEntity> findAllByStoreName(@Param(value="searchkey") String searchkey);
}
