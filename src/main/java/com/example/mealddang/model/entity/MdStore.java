package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name = "md_store_master")
public class MdStore {

    @Id @Column(name = "store_id_n")
    private String storeId;
    
    // private String storeIdC;
    // private String storeIdK;
    // private String storeIdN;    
    // private String storeIdY;

    private String storeName;

    // 경도(lon,x) 위도(lat,y)
    private String storeX;
    private String storeY;

    // private String storeCategory;

    @Column(name = "store_road_address_n")
    private String storeAddr;

    @Column(name = "store_phone")
    private String storeTel;

    @Column(name = "store_image_url") //대표이미지
    private String storeImgUrl;
    @Column(name = "store_menu_url_n") // 메뉴페이지
    private String storeMenuUrlN;
    @Column(name = "store_homepage")
    private String storeUrl;    // 식당홈페이지

}