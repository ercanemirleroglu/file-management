package com.ercanemirleroglu.etscase.app.authentication;

import com.ercanemirleroglu.etscase.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser loadUserByUsername(String username) {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .build();
        return AppUser.of(userDto);
    }
}
