package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.CorpAndCategoryDto;
import com.bokjips.server.domain.corp.dto.CorpListResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.entity.CorpCategory;
import com.bokjips.server.domain.corp.repository.CorpCategoryRepository;
import com.bokjips.server.domain.corp.repository.CorpGoodsRepository;
import com.bokjips.server.domain.corp.repository.CorpRepository;
import com.bokjips.server.domain.welfare.entity.Welfare;
import com.bokjips.server.domain.welfare.repository.WelfareRepository;
import com.bokjips.server.service.SearchService;
import com.bokjips.server.util.dto.PageRequestDto;
import com.bokjips.server.util.dto.PageResponseDto;
import com.bokjips.server.util.module.PageModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class SearchServiceImpl implements SearchService {
    private final PageModule pageModule;
    private final CorpRepository corpRepository;
    private final CorpCategoryRepository corpCategoryRepository;
    private final CorpGoodsRepository corpGoodsRepository;
    private final WelfareRepository welfareRepository;
    @Override
    public PageResponseDto<CorpListResponseDto, CorpAndCategoryDto> searchCorpName(Integer page, Integer size, String keyword) throws Exception {
        PageRequestDto pageRequestDto = pageModule.makePage(page,size);

        List<Corp> corpList = corpRepository.selectCorpName(keyword);


        List<CorpAndCategoryDto> entityList = new ArrayList<>();
        List<String> categoryValue;

        for(Corp corp:corpList) {
            categoryValue = new ArrayList<>();
            List<CorpCategory> list = corpCategoryRepository.findByCorpId(corp.getId());
            for(CorpCategory c: list) {
                categoryValue.add(c.getCategory());
            }
            entityList.add(CorpAndCategoryDto.builder()
                            .corp(corp)
                            .category(categoryValue)
                    .build());

        }
        final Page<CorpAndCategoryDto> corpAndCategoryDto = new PageImpl<>(entityList, pageRequestDto.getPageable(Sort.by("modDate").descending()), entityList.size());
        Function<CorpAndCategoryDto, CorpListResponseDto> fn = (data -> corpPageToDto(data,corpGoodsRepository.countByCorpId(data.getCorp().getId())));
        PageResponseDto<CorpListResponseDto, CorpAndCategoryDto> pageResponseDto =new PageResponseDto<>(corpAndCategoryDto, fn);

        for(CorpListResponseDto corp : pageResponseDto.dtoList) {
            List<Welfare> welfareListEntity = welfareRepository.findByCorpId(corp.getCorp_id());
            List<String> welfareList = new ArrayList<>();
            for(Welfare w : welfareListEntity) {
                welfareList.add(w.getSubtitle());
            }
            corp.setWelfareList(welfareList);
        }
        return pageResponseDto;
    }
}
