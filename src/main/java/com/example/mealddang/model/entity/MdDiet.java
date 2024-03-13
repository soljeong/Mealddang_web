package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "md_diet2")
@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MdDiet {

    @Id @Column(name = "diet_id")
    private int dietId;

    @Column(name = "age_start", columnDefinition = "varchar(2)")
    private String ageStart;
    @Column(name = "age_end", columnDefinition = "varchar(2)")
    private String ageEnd;
    private String gender;

    @Column(name = "energy_kcal")
    private float kcal;
    @Column(name = "protein_g")
    private float protein;
}
