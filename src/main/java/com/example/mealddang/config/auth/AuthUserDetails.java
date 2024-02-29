package com.example.mealddang.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.mealddang.model.entity.MdUser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthUserDetails implements UserDetails {
    @Autowired
    private MdUser mdUser;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return mdUser.getRole().getKey();
            }
        });
        return authorities;
    }
    @Override
    public String getPassword() {
        return mdUser.getPassword();
    }
    @Override
    public String getUsername() {
        return mdUser.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
