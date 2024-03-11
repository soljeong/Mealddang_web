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
public class MdImgIsolated {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isoId;

    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private MdUser username;

    @ManyToOne @JoinColumn(name = "upload_id", referencedColumnName = "uploadId")
    private MdImgUpload uploadId;

    @Column(columnDefinition = "varchar(20)")
    private String isoResult;
    private String isoStored;
}
