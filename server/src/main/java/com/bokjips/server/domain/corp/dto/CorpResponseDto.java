package com.bokjips.server.domain.corp.dto;

import com.bokjips.server.domain.welfare.dto.WelfareResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CorpResponseDto {
    private final String corp_id;
    private final String name;
    private final String site;
    private final String career;
    private final List<String> category;
    private final boolean stock;
    private final List<String> good;
    private final String image;
    Map<String,List<WelfareResponseDto>> welfareList;
    private final LocalDateTime regDate;
    private final LocalDateTime modDate;
}
