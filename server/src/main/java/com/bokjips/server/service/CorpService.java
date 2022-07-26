package com.bokjips.server.service;

import com.bokjips.server.domain.corp.dto.*;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.entity.CorpCategory;
import com.bokjips.server.domain.welfare.dto.WelfareRequestDto;
import com.bokjips.server.domain.welfare.dto.WelfareResponseDto;
import com.bokjips.server.domain.welfare.entity.Welfare;
import com.bokjips.server.util.dto.PageResponseDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CorpService {
    CorpResponseDto insertCorp(CorpRequestDto dto) throws Exception;

    CorpResponseDto selectCorp(String corp_id, String user_id) throws Exception;

    CorpResponseDto updateCorp(String corp_id, CorpRequestDto dto) throws Exception;

    String deleteCorp(String corp_id) throws Exception;

    PageResponseDto<CorpListResponseDto, CorpCategory> selectCorpList(Integer page, Integer size, String keyword) throws Exception;

    List<CorpMiniResponseDto> selectMini(CorpMiniRequestDto dto) throws Exception;

    String updateGoods (GoodsRequestDto dto) throws Exception;

    PageResponseDto<CorpListResponseDto,CorpCategory> selectGoodList(String user_id, Integer page, Integer size);

    default Corp dtoToCorpEntity(CorpRequestDto dto){
        return Corp.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.getName())
                .site(dto.getSite())
                .career(dto.getCareer())
                .stock(dto.isStock())
                .image(dto.getImage())
                .build();
    }

    default CorpResponseDto corpEntityToDto(Corp entity, Map<String, List<WelfareResponseDto>> welfareList, boolean state, Long goodSize, List<String> category) {
        return CorpResponseDto.builder()
                .corp_id(entity.getId())
                .career(entity.getCareer())
                .category(category)
                .image(entity.getImage())
                .good(goodSize)
                .goodState(state)
                .name(entity.getName())
                .modDate(entity.getModDate())
                .regDate(entity.getRegDate())
                .site(entity.getSite())
                .stock(entity.isStock())
                .welfareList(welfareList)
                .build();
    }

    default CorpListResponseDto corpPageToDto(CorpCategory entity, Long goodSize,List<String> category) {
        return CorpListResponseDto.builder()
                .corp_id(entity.getCorp().getId())
                .career(entity.getCorp().getCareer())
                .image(entity.getCorp().getImage())
                .good(goodSize)
                .category(category)
                .name(entity.getCorp().getName())
                .modDate(entity.getCorp().getModDate())
                .regDate(entity.getCorp().getRegDate())
                .site(entity.getCorp().getSite())
                .stock(entity.getCorp().isStock())
                .build();
    }

    default Welfare dtoToWelfareEntity(Corp corpEntity,String title,WelfareRequestDto dto) {
        return Welfare.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .subtitle(dto.getSubTitle())
                .options(dto.getOptions())
                .corp(corpEntity)
                .build();
    }

    default CorpMiniResponseDto entityToMiniDto(Corp entity, Long goodSize) {
        return CorpMiniResponseDto.builder()
                .corp_id(entity.getId())
                .name(entity.getName())
                .good(goodSize)
                .build();
    }
}
