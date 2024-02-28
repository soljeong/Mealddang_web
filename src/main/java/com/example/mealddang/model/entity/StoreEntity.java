package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity(name = "restaurants_naver")
public class StoreEntity {
    @Id @Column(name = "restaurant_id")
    private int storeId;
    @Column(name = "name")
    private String storeName;
    @Column(name = "roadAddress")
    private String roadAddr;
    @Column(name = "x")
    private String longitude;
    @Column(name = "y")
    private String latitude;
}
