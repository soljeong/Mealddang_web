package com.example.mealddang.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MdNutResult extends BaseTimeEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    
    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private MdUser username;

    @ManyToOne @JoinColumn(name = "origin_path", referencedColumnName = "origin_path")
    private MdImgUpload originPath;
    private String resultPath;
    private String resultLabel;

    // 이하는 모델분석 결과(resultLabel)를 토대로 nut_info table에서 가져오는 속성
    private Float kcal;
    private Float carbo_g;
    private Float fat_g;
    private Float protein_g;
}
