package com.example.mealddang.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.UserEntity;
import com.example.mealddang.model.repository.UserRepository;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.getUserEntityByUsername(username);

        if(userEntity != null) {
            return new AuthUserDetails(userEntity);
        }
        throw new UsernameNotFoundException(username);
    }
    
}
