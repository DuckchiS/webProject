package com.example.web.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.web.entity.UserEntity;

public class CustomUserDetails implements UserDetails{
	private final UserEntity user;
	
	public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    public String getNickname() {
        return user.getMNickname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getMRole().toUpperCase()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getMPw();
    }

    @Override
    public String getUsername() {
        return user.getMId();
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
