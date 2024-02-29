package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name = "restaurants_yogiyo")
public class MdStore {
    @Id @Column(name = "restaurant_id")
    private int storeId;

    @Column(name = "name")
    private String storeName;

    @Column(name = "phone")
    private String storeTel;

    @Column(name = "address")
    private String storeAddr;
    @Column(name = "lng")
    private String storeX;
    @Column(name = "lat")
    private String storeY;

    @Column(name = "review_avg")
    private String storeScore;

    @Column(name = "thumbnail_url")
    private String storeImg;

    @Column(name = "categories")
    private String storeCategory;

    // 리뷰분석 통해 도출되는 컬럼
    // private String storeBest;
}
