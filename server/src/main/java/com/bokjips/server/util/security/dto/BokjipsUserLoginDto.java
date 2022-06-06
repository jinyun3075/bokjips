package com.bokjips.server.util.security.dto;

import lombok.Data;

@Data
public class BokjipsUserLoginDto {
    String email;
    String password;
}
