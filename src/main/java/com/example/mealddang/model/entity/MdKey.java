package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;



@Getter @Entity(name="md_restaurant_keyword")
public class MdKey {
    @Id @Column(name = "keyword_id")
    private Long keywordid;

    @Column(name = "restaurant_id")
    private Long restaurantid;

    @Column(name = "keyword")
    private Long keyword;

}
