package com.bokjips.server.domain.corp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CorpMiniResponseDto {
    String name;
    String corp_id;
    Long good;
}
