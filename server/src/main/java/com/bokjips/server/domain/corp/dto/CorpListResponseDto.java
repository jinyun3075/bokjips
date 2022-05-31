package com.bokjips.server.domain.corp.dto;

import com.bokjips.server.domain.welfare.dto.WelfareResponseDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class CorpListResponseDto {
    private final String corp_id;
    private final String name;
    private final String site;
    private final String career;
    private final List<String> category;
    private final boolean stock;
    private final Long good;
    private final String image;
    List<String> welfareList;
    private final LocalDateTime regDate;
    private final LocalDateTime modDate;
}
