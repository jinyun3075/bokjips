package com.bokjips.server.controller;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.domain.corp.dto.CorpResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.service.CorpService;
import com.bokjips.server.util.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corp")
@Log4j2
@RequiredArgsConstructor
public class ApiCorpController {

    private final CorpService corpService;

    @PostMapping("/insert")
    public ResponseEntity<CorpResponseDto> insertCorp(@RequestBody CorpRequestDto dto) throws Exception {
        return new ResponseEntity<>(corpService.insertCorp(dto), HttpStatus.OK);
    }

    @GetMapping("/select/{corp_id}")
    public ResponseEntity<CorpResponseDto> selectCorp(@PathVariable String corp_id) throws Exception{
        return new ResponseEntity<>(corpService.selectCorp(corp_id),HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<PageResponseDto<CorpResponseDto, Corp>> selectCorpList(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) throws Exception {
        return new ResponseEntity<>(corpService.selectCorpList(page,size),HttpStatus.OK);
    }

    @PutMapping("/update/{corp_id}")
    public ResponseEntity<CorpResponseDto> updateCorp(@PathVariable String corp_id, @RequestBody CorpRequestDto dto) throws Exception{
        return new ResponseEntity<>(corpService.updateCorp(corp_id,dto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{corp_id}")
    public ResponseEntity<String> deleteCorp(@PathVariable String corp_id) throws Exception {
        return new ResponseEntity<>(corpService.deleteCorp(corp_id),HttpStatus.OK);
    }
}
