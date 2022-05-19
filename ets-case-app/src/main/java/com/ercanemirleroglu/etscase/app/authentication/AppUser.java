package com.ercanemirleroglu.etscase.app.authentication;

import com.ercanemirleroglu.etscase.domain.dto.UserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class AppUser implements UserDetails {

    private static final long serialVersionUID = -984423694821246583L;

    private Long id;
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> roles;

    public static AppUser of(UserDto user) {
        AppUser appUser = new AppUser();
        appUser.id = user.getId();
        appUser.username = user.getUsername();
        appUser.password = user.getPassword();
        appUser.roles = List.of(new SimpleGrantedAuthority("USER"));
        return appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

