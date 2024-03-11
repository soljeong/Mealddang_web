package com.example.mealddang.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdNutInfo;
import com.example.mealddang.model.entity.MdStore;

public interface MdNutInfoRepository extends JpaRepository<MdNutInfo,Double>{
    @Query(value = "select energy_kcal,cafbohydrate_g,fat_g,protein_g from md_nut_info where energy_kcal like CONCAT('%', :searchkey, '%')", nativeQuery = true)
    public List<MdStore> findAllByStoreName(@Param(value="searchkey") String searchkey);
}
