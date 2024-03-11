package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder @Getter @AllArgsConstructor @NoArgsConstructor
public class MdImgUpload extends BaseTimeEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_id")
    private long uploadId;

    private String originalName;
    private String storedName;

    @Column(nullable = true)
    private long fileSize;

    // GPS 정보: 위도(latitude=y), 경도(longitude=x)
    @Column(name = "img_x")
    private float imgX;
    @Column(name = "img_y")
    private float imgY;
}