package com.ercanemirleroglu.etscase.domain.dto;

import lombok.*;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
}
