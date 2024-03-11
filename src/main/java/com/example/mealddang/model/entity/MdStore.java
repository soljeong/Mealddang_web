package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name = "md_store_master")
public class MdStore {
    @Id @Column(name = "store_id_n")
    private String storeId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_phone")
    private String storeTel;

    @Column(name = "store_roadaddress_naver")
    private String storeAddr;
    @Column(name = "store_x")
    private String storeX;
    @Column(name = "store_y")
    private String storeY;

    @Column(name = "store_imageurl") //대표이미지 URl 에정
    private String storeScore;

    @Column(name = "store_menu_url_naver") // 대표 홈페이지 URL 예정
    private String storeImg;

    // @Column(name = "categories")
    // private String storeCategory;

    // 리뷰분석 통해 도출되는 컬럼
    // private String storeBest;
}
