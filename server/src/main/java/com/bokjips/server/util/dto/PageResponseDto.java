package com.bokjips.server.util.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PageResponseDto<DTO,EN> {
    public List<DTO> dtoList;

    public int totalPage;

    public int page;

    public int size;

    public boolean prev,next;

    public int start, end;

    public List<Integer> pageList;

    public PageResponseDto(Page<EN> result, Function<EN,DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber()+1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(page/5.0))*5;

        start = tempEnd -4;

        prev = start>1;

        end = totalPage > tempEnd?tempEnd:totalPage;

        next =totalPage>tempEnd;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}
