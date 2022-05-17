package com.bokjips.server.service;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.domain.corp.dto.CorpResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;

import java.util.UUID;

public interface CorpService {
    public CorpResponseDto insertCorp(CorpRequestDto dto);

    public CorpResponseDto selectCorp(UUID corp_id);
    default Corp dtoToEntity(CorpRequestDto dto){
        return Corp.builder()
                .name(dto.getName())
                .site(dto.getSite())
                .career(dto.getCareer())
                .category(dto.getCategory())
                .stock(dto.isStock())
                .image(dto.getImage())
                .good(0l)
                .build();
    }

    default CorpResponseDto entityToDto(Corp entity) {
        return CorpResponseDto.builder()
                .corp_id(entity.getId())
                .career(entity.getCareer())
                .category(entity.getCategory())
                .image(entity.getImage())
                .good(entity.getGood())
                .name(entity.getName())
                .modDate(entity.getModDate())
                .regDate(entity.getRegDate())
                .site(entity.getSite())
                .stock(entity.isStock())
                .build();
    }
}
