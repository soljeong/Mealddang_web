package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter@Entity(name="md_nut_info")
public class MdNutInfo {
    @Id @Column(name = "energy_kcal")
    private Double totalKcal;

    @Column(name = "cafbohydrate_g")
    private String tan;

    @Column(name = "protein_g")
    private String dan;
    
    @Column(name = "fat_g")
    private String zi;
}
