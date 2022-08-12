package com.bokjips.server.controller;

import com.bokjips.server.domain.corp.dto.CorpAndCategoryDto;
import com.bokjips.server.domain.corp.dto.CorpListResponseDto;
import com.bokjips.server.service.SearchService;
import com.bokjips.server.util.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Log4j2
public class ApiSearchController {

    private final SearchService service;

    @GetMapping("/{keyword}")
    public ResponseEntity<PageResponseDto<CorpListResponseDto, CorpAndCategoryDto>> selectUserAndWorkBook(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @PathVariable String keyword) throws Exception{
        return new ResponseEntity<>(service.searchCorpName(page,size,keyword), HttpStatus.OK);
    }
}