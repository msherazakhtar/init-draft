package com.expense.tracker.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final String email;
    private final String password;
    private final Boolean is_verified;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long userId, String email, String password,Boolean is_verified,
                             Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.is_verified = is_verified;
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }
    public Boolean getIs_verified() {return is_verified;}

    @Override public String getUsername() { return email; }
    @Override public String getPassword() { return password; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}