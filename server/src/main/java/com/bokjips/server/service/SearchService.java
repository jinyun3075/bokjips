package com.bokjips.server.service;

import com.bokjips.server.domain.corp.dto.CorpAndCategoryDto;
import com.bokjips.server.domain.corp.dto.CorpListResponseDto;
import com.bokjips.server.util.dto.PageResponseDto;

public interface SearchService {
    PageResponseDto<CorpListResponseDto, CorpAndCategoryDto> searchCorpName(Integer page, Integer size, String keyword) throws Exception;
    default CorpListResponseDto corpPageToDto(CorpAndCategoryDto entity, Long goodSize) {
        return CorpListResponseDto.builder()
                .corp_id(entity.getCorp().getId())
                .career(entity.getCorp().getCareer())
                .image(entity.getCorp().getImage())
                .good(goodSize)
                .category(entity.getCategory())
                .name(entity.getCorp().getName())
                .modDate(entity.getCorp().getModDate())
                .regDate(entity.getCorp().getRegDate())
                .site(entity.getCorp().getSite())
                .stock(entity.getCorp().isStock())
                .build();
    }
}
