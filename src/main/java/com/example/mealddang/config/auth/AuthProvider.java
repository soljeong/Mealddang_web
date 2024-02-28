package com.example.mealddang.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthUserService authUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = (String)authentication.getCredentials();
        // ID 검증
        UserDetails userDetails = (AuthUserDetails)authUserService.loadUserByUsername(name);
        if(userDetails == null){
            throw new UsernameNotFoundException("There is no username >> "+name);
        }
        // PW 검증 
        else if (isNotMatches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Your password is incorrect.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    private boolean isNotMatches(String password, String encodePassword) {
        return !bCryptPasswordEncoder.matches(password, encodePassword);
    }
}
