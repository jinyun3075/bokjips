package com.bokjips.server.controller;

import com.bokjips.server.domain.user.dto.UserRequestDto;
import com.bokjips.server.domain.user.dto.UserResponseDto;
import com.bokjips.server.service.BokjipsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class ApiUserController {

    private final BokjipsUserService bokjipsUserService;

    @PostMapping("/insert")
    public ResponseEntity<UserResponseDto> insertUser(@RequestBody UserRequestDto dto) throws Exception{
        return new ResponseEntity<>(bokjipsUserService.insertUser(dto), HttpStatus.OK);
    }
}
