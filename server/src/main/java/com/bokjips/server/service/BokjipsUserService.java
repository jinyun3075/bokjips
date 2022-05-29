package com.bokjips.server.service;

import com.bokjips.server.domain.user.dto.UserRequestDto;
import com.bokjips.server.domain.user.dto.UserResponseDto;
import com.bokjips.server.domain.user.entity.BokjipsUser;
import com.bokjips.server.domain.user.entity.UserRole;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public interface BokjipsUserService {

    UserResponseDto insertUser(UserRequestDto dto) throws Exception;

    default BokjipsUser dtoToEntity (UserRequestDto dto) {
        Set<UserRole> role = new HashSet<>();
        role.add(UserRole.USER);
        return BokjipsUser.builder()
                .id(UUID.randomUUID().toString())
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .roleSet(role)
                .build();
    }

    default UserResponseDto entityToDto(BokjipsUser entity){
        return UserResponseDto.builder()
                .user_id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .build();
    }

}
