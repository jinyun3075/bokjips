package com.bokjips.server.controller;

import com.bokjips.server.domain.comments.dto.CommentsRequestDto;
import com.bokjips.server.domain.comments.dto.CommentsResponseDto;
import com.bokjips.server.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/comments")
public class ApiCommentsController {

    private final CommentsService commentsService;

    @PostMapping("/insert")
    public ResponseEntity<CommentsResponseDto> insertComments(@RequestBody CommentsRequestDto dto) throws Exception {
        return new ResponseEntity<>(commentsService.insertComments(dto), HttpStatus.OK);
    }
}
