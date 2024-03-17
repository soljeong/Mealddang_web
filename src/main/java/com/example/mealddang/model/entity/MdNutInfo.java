package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "md_nut_info")
@Getter
@NoArgsConstructor @AllArgsConstructor @ToString
public class MdNutInfo {

    @Id
    @Column(name = "class_label")
    private String classLabel;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "serving_size_g")
    private Float servingSize;

    @Column(name = "energy_kcal")
    private Float energyKcal;

    @Column(name = "carbohydrate_g")
    private Float carbohydrate;

    @Column(name = "total_sugars_g")
    private Float totalSugars;

    @Column(name = "fat_g")
    private Float fat;

    @Column(name = "protein_g")
    private Float protein;

    @Column(name = "calcium_mg")
    private Float calcium;

    @Column(name = "phosph_mg")
    private Float phosph;

    @Column(name = "sodium_mg")
    private Float sodium;

    @Column(name = "potassium_mg")
    private Float potassium;

    @Column(name = "magnesium_mg")
    private Float magnesium;

    @Column(name = "iron_mg")
    private Float iron;

    @Column(name = "zinc_mg")
    private Float zinc;

    @Column(name = "cholesterol_mg")
    private Float cholesterol;

    @Column(name = "transfat_g")
    private Float transfat;
}
