package com.example.mealddang.config.oauth;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdUserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private MdUserRepository mdUserRepository;
    private final HttpSession httpSession;

    public CustomOAuth2UserService(MdUserRepository p_mdUserRepository, HttpSession httpSession) {
        this.mdUserRepository = p_mdUserRepository;
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId= userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        MdUser mdUser = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(mdUser));

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(mdUser.getRole().getKey())),
            attributes.getAttributes(),
            attributes.getNameAttributeKey()
        );
    }

    private MdUser saveOrUpdate(OAuthAttributes attributes) {
        MdUser mdUser = mdUserRepository.findByEmail(attributes.getEmail())
        .map(entity -> entity.update(attributes.getUsername())).orElse(attributes.toEntity());

        return mdUserRepository.save(mdUser);
    }
    
}
