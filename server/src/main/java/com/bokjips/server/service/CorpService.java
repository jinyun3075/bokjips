package com.bokjips.server.service;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.domain.corp.dto.CorpResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.welfare.dto.WelfareRequestDto;
import com.bokjips.server.domain.welfare.dto.WelfareResponseDto;
import com.bokjips.server.domain.welfare.entity.Welfare;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CorpService {
    public CorpResponseDto insertCorp(CorpRequestDto dto) throws IOException;

    public CorpResponseDto selectCorp(UUID corp_id) throws IOException;

    public CorpResponseDto updateCorp(UUID corp_id, CorpRequestDto dto) throws IOException;

    public String deleteCorp(UUID corp_id) throws IOException;
    default Corp dtoToCorpEntity(CorpRequestDto dto){
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

    default CorpResponseDto corpEntityToDto(Corp entity, Map<String, List<WelfareResponseDto>> welfareList) {
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
                .welfareList(welfareList)
                .build();
    }

    default Welfare dtoToWelfareEntity(Corp corpEntity,WelfareRequestDto dto) {
        return Welfare.builder()
                .title(dto.getTitle())
                .subtitle(dto.getSubTitle())
                .options(dto.getOptions())
                .corp(corpEntity)
                .build();
    }
}
