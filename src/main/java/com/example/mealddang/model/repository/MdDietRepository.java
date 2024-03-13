package com.example.mealddang.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.MdDiet;

public interface MdDietRepository extends JpaRepository<MdDiet, Integer> {
    
    @Query(value = "SELECT * \n" + //
                "FROM md_diet2 \n" + //
                "WHERE gender = :p_gender \n" + //
                "AND (YEAR(CURDATE()) - :p_birthyear) BETWEEN age_start AND age_end", nativeQuery = true)
    public MdDiet getDiet(@Param(value = "p_gender") String p_gender, @Param(value = "p_birthyear") int p_birthyear);
}
