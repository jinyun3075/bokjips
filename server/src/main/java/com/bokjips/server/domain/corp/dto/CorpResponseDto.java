package com.bokjips.server.domain.corp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CorpResponseDto {
    private final UUID corp_id;
    private final String name;
    private final String site;
    private final String career;
    private final List<String> category;
    private final boolean stock;
    private final Long good;
    private final String image;
    private final LocalDateTime regDate;
    private final LocalDateTime modDate;
}
