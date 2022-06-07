package com.bokjips.server.controller;

import com.bokjips.server.domain.comments.dto.CommentsRequestDto;
import com.bokjips.server.domain.comments.dto.CommentsResponseDto;
import com.bokjips.server.domain.comments.entity.Comments;
import com.bokjips.server.service.CommentsService;
import com.bokjips.server.util.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/select/{corp_id}")
    public ResponseEntity<PageResponseDto<CommentsResponseDto,Comments>> selectListComments(@PathVariable String corp_id,@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) throws Exception{
        return new ResponseEntity<>(commentsService.selectListComments(corp_id, page, size),HttpStatus.OK);
    }


}
