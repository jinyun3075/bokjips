package com.bokjips.server.domain.corp.dto;

import lombok.Data;

import java.util.List;


@Data
public class CorpRequestDto {
    private final String name;
    private final String site;
    private final String career;
    private final List<String> category;
    private final boolean stock;
    private final String image;

}
