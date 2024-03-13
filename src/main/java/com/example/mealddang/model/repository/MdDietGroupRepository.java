package com.example.mealddang.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdDietGroup;
import com.example.mealddang.model.entity.MdUser;

public interface MdDietGroupRepository extends JpaRepository<MdDietGroup, MdUser> {
    // 유저 개인정보에 따라 식단그룹 배정
    // @Query(value = "SELECT CASE WHEN (user_age BETWEEN(15,18) & user_gender='M' THEN diet_gorup = 1)", nativeQuery = true)
    // public Integer setDietGroup(@Param(value = "age") Integer age, @Param(value = "gender") String gender);
}
