package com.example.mealddang.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdUserRepository;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private MdUserRepository mdUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MdUser mdUser = mdUserRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
            
        return new AuthUserDetails(mdUser);
    }
}
