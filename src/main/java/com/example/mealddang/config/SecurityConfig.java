package com.example.mealddang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.mealddang.config.handler.LoginAuthFailureHandler;
import com.example.mealddang.config.handler.LoginAuthSuccessHandler;
import com.example.mealddang.config.handler.LogoutAuthSuccessHandler;
import com.example.mealddang.config.oauth.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginAuthFailureHandler loginAuthFailureHandler;
    @Autowired
    private LoginAuthSuccessHandler loginAuthSuccessHandler;
    @Autowired
    private LogoutAuthSuccessHandler logoutAuthSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        // 소셜 로그인 설정
        // 로그인 무반응 문제: SessionCreationPolicy.STATELESS -> IF_REQUIRED 해결
        http.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers(new MvcRequestMatcher(introspector, "/api/user")).permitAll())
        .oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService)));

        //인증 인가 설정
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/user/**").authenticated()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
        )

        .oauth2Login(oauth2Login -> oauth2Login
        .loginPage("/loginform")
        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService))
        )

        .formLogin(formLogin -> formLogin
            .loginPage("/loginform")
            .loginProcessingUrl("/loginprocess")
            .successHandler(loginAuthSuccessHandler)
            .failureHandler(loginAuthFailureHandler)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutAuthSuccessHandler)
            .permitAll()
        );
        
        return http.build();
    }
}
