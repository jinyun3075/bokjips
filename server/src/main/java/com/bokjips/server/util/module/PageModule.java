package com.bokjips.server.util.module;

import com.bokjips.server.util.dto.PageRequestDto;

public class PageModule {
    public PageRequestDto makePage(Integer page, Integer size) {
        PageRequestDto pageRequestDTO = new PageRequestDto();
        if (page != null && size != null) {
            pageRequestDTO.setSize(size);
            pageRequestDTO.setPage(page);
        }
        return pageRequestDTO;
    }
}
