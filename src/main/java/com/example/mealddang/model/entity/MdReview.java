package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name = "reviews_catchtable")
public class MdReview {
    @Id @Column(name = "review_id")
    private long reviewId;

    @Column(name = "restaurant_id")
    private String storeId;

    @Column(name = "shop_name")
    private String storeName;

    @Column(name = "review_content")
    private String review;
}
