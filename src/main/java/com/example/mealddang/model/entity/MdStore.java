package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name = "md_store_master")
public class MdStore {
    @Id @Column(name = "store_id_n")
    private String storeId;

    @Column(name = "상호명")
    private String storeName;

    @Column(name = "phone")
    private String storeTel;

    @Column(name = "roadaddress")
    private String storeAddr;
    @Column(name = "경도")
    private String storeX;
    @Column(name = "위도")
    private String storeY;

    //@Column(name = "review_avg") //대표이미지 URl 에정
    //private String storeScore;

    //@Column(name = "thumbnail_url") // 대표 홈페이지 URL 예정
    //private String storeImg;

    // @Column(name = "categories")
    // private String storeCategory;

    // 리뷰분석 통해 도출되는 컬럼
    // private String storeBest;
}
