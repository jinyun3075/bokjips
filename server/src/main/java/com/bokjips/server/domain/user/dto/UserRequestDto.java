package com.bokjips.server.domain.user.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String name;
    private String password;
}
