package com.example.mealddang.config.oauth;

import java.util.Map;

import com.example.mealddang.config.constant.Role;
import com.example.mealddang.model.entity.MdUser;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String email;
    private MdUser mdUser;

    public static OAuthAttributes of(
        String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
            if("naver".equals(registrationId)) {
                return ofNaver("id", attributes);
            } else if ("kakao".equals(registrationId)) {
                return ofKakao("id", attributes);
            }
            return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(
        String userNameAttributeName, Map<String, Object> attributes) {
            return OAuthAttributes.builder()
            .username((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    private static OAuthAttributes ofNaver(
        String userNameAttributeName, Map<String, Object> attributes) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");

            return OAuthAttributes.builder()
            .username((String) response.get("name"))
            .email((String) response.get("email"))
            .attributes(response)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    private static OAuthAttributes ofKakao(
        String userNameAttributeName, Map<String, Object> attributes) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> account = (Map<String, Object>) attributes.get("profile");

            return OAuthAttributes.builder()
            .username((String) account.get("nickname"))
            .email((String) response.get("email"))
            .attributes(response)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    public MdUser toEntity() {
        mdUser.setUsername(username);
        mdUser.setEmail(email);
        mdUser.setRole(Role.USER);
        return mdUser;
    }
}
