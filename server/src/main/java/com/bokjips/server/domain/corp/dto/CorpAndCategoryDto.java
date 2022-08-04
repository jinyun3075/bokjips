package com.bokjips.server.domain.corp.dto;

import com.bokjips.server.domain.corp.entity.Corp;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CorpAndCategoryDto {
    Corp corp;
    List<String> category;
}
