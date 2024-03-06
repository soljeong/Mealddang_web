package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name = "restaurants_catchtable")
public class MdStore {
    @Id @Column(name = "shopref")
    private String storeId;

    @Column(name = "shopname")
    private String storeName;

    @Column(name = "dispshopphone")
    private String storeTel;

    @Column(name = "shopaddress")
    private String storeAddr;
    @Column(name = "lon")
    private String storeX;
    @Column(name = "lat")
    private String storeY;

    // @Column(name = "review_avg")
    // private String storeScore;

    // @Column(name = "thumbnail_url")
    // private String storeImg;

    // @Column(name = "categories")
    // private String storeCategory;

    // 리뷰분석 통해 도출되는 컬럼
    // private String storeBest;
}
