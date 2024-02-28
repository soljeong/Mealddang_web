package com.example.mealddang.config.oauth;

import java.io.Serializable;

import com.example.mealddang.model.entity.UserEntity;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String username;
    private String email;

    public SessionUser(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
    }
}
// 세션유저 클래스는 인증용 사용자 정보만 필요합니다.
