package com.example.mealddang.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter 
@Entity(name="md_restaurant_keyword")
public class MdKey {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "keyword_id")
    private Long keywordId;


    @Column(name = "store_id_n") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storeIdN;

    @Column(name = "store_name") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storeName;

    @Column(name = "store_phone") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storePhone;

    @Column(name = "store_road_address_n") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storeRoadAddressN;

    @Column(name = "store_x") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storeX;

    @Column(name = "store_y") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storeY;

    @Column(name = "store_image_url") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storeImageUrl;

    @Column(name = "store_menu_url_n") // 쿼리 결과에 해당하는 필드와 매핑됩니다.
    private String storeMenuUrlN;

}
