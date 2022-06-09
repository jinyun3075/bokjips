package com.bokjips.server.domain.corp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CorpMiniRequestDto {
    String corp_id;
    List<String> category;
    boolean stock;
}