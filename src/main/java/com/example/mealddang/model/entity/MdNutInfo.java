package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MdNutInfo {
    
    @Id @Column(name = "food_name")
    private String foodName;

    @Column(name = "serving_size_g")
    private Float servingSize_g;
    
    @Column(name = "energy_kcal")
    private Float kcal;
    @Column(name = "carbohydrate_g")
    private Float carbo_g;
    @Column(name = "total_sugars_g")
    private Float sugar_g;
    @Column(name = "fat_g")
    private Float fat_g;
    @Column(name = "protein_g")
    private Float protein_g;
    @Column(name = "calcium_mg")
    private Float calcium_mg;
    
}
