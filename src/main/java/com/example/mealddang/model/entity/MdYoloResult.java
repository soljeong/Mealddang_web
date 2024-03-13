package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
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
public class MdYoloResult {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private long resultId;

    @Column(columnDefinition = "varchar(20)")
    private String resultLabel;

    @ManyToOne @JoinColumn(name = "img_path", referencedColumnName = "img_path")
    private MdImgUpload imgPath;

    @Column(name = "result_conf")
    private Float resultConf;
    @Column(name = "result_x")
    private Integer resultX;
    @Column(name = "result_y")
    private Integer resultY;
    @Column(name = "result_w")
    private Integer resultW;
    @Column(name = "result_h")
    private Integer resultH;
}
