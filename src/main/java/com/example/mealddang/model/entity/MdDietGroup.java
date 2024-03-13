package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class MdDietGroup {
    
    @Id
    private String id;
    @OneToOne @MapsId
    private MdUser username;

    @Column(name = "user_age")
    private Integer userAge;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "diet_group")
    private Integer dietGroup;

    // 생성자
    public MdDietGroup(MdUser p_username) {
        this.username = p_username;
    }
}
