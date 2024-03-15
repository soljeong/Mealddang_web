package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "md_img_upload")
@Builder @Getter @AllArgsConstructor @NoArgsConstructor
public class MdImgUpload extends BaseTimeEntity {
    
    @Id @Column(name = "origin_path")
    private String originPath;

    @Column(name = "origin_name")
    private String originName;

    @Column(nullable = true)
    private long fileSize;

    // GPS 정보: 위도(latitude=y), 경도(longitude=x)
    @Column(name = "photo_x", columnDefinition = "varchar(30)")
    private String photoX;
    @Column(name = "photo_y", columnDefinition = "varchar(30)")
    private String photoY;
}