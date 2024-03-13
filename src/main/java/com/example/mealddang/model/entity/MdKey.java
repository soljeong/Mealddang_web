package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name="md_restaurant_keyword")
public class MdKey {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "keyword_id")
    private Long keywordId;

    @Column(columnDefinition = "restaurant_id")
    private String storeIdN;

    private String keyword;
}