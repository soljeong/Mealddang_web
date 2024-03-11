package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity(name = "md_review")
public class MdReview {
    @Id @Column(name = "store_id_o")
    private String storeName;

    @Column(name = "review")
    private String review;
}
