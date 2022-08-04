package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.*;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.entity.CorpCategory;
import com.bokjips.server.domain.corp.entity.CorpGoods;
import com.bokjips.server.domain.corp.repository.CorpCategoryRepository;
import com.bokjips.server.domain.corp.repository.CorpGoodsRepository;
import com.bokjips.server.domain.corp.repository.CorpRepository;
import com.bokjips.server.domain.user.repository.BokjipsUserRepository;
import com.bokjips.server.domain.welfare.dto.WelfareRequestDto;
import com.bokjips.server.domain.welfare.dto.WelfareResponseDto;
import com.bokjips.server.domain.welfare.entity.Welfare;
import com.bokjips.server.domain.welfare.repository.WelfareRepository;
import com.bokjips.server.service.CorpService;
import com.bokjips.server.util.dto.PageRequestDto;
import com.bokjips.server.util.dto.PageResponseDto;
import com.bokjips.server.util.module.PageModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CorpServiceImpl implements CorpService {

    private final PageModule pageModule;
    private final CorpRepository corpRepository;
    private final WelfareRepository welfareRepository;
    private final CorpGoodsRepository corpGoodsRepository;
    private final BokjipsUserRepository bokjipsUserRepository;
    private final CorpCategoryRepository corpCategoryRepository;

    @Override
    public CorpResponseDto insertCorp(CorpRequestDto dto) throws Exception {

        Corp entity = corpRepository.save(dtoToCorpEntity(dto));
        List<String> categories = new ArrayList<>();
        for(String category : dto.getCategory()) {
            corpCategoryRepository.save(CorpCategory.builder()
                            .category(category)
                            .corp(entity)
                    .build());
            categories.add(category);
        }
        for (String key : dto.getWelfareList().keySet()) {
            for(WelfareRequestDto welfare: dto.getWelfareList().get(key)){
                welfareRepository.save(dtoToWelfareEntity(entity, key, welfare));
            }
        }

        Map<String, List<WelfareResponseDto>> welfareList = new HashMap<>();
        for (String key : dto.getWelfareList().keySet()) {
            for(WelfareRequestDto welfare: dto.getWelfareList().get(key)){
                List<WelfareResponseDto> list = welfareList.getOrDefault(key, new ArrayList<>());
                list.add(WelfareResponseDto.builder()
                        .subTitle(welfare.getSubTitle())
                        .options(welfare.getOptions()).build());
                welfareList.put(key, list);
            }
        }

        return corpEntityToDto(entity, welfareList, false, 0l,categories);
    }

    @Override
    public CorpResponseDto selectCorp(String corp_id, String user_id) throws Exception {
        Corp entity = corpRepository.findById(corp_id).orElseThrow(()->new Exception("존재하지않는 아이디입니다."));
        List<Welfare> welfareListEntity = welfareRepository.findByCorpId(corp_id);
        List<CorpCategory> categories = corpCategoryRepository.findByCorpId(corp_id);
        List<String> categoriesValue = new ArrayList<>();
        for(CorpCategory category: categories) {
            categoriesValue.add(category.getCategory());
        }

        Map<String, List<WelfareResponseDto>> welfareList = new HashMap<>();
        for(Welfare w : welfareListEntity) {
            String key = w.getTitle();
            List<WelfareResponseDto> list = welfareList.getOrDefault(key, new ArrayList<>());
            list.add(WelfareResponseDto.builder()
                            .subTitle(w.getSubtitle())
                            .options(w.getOptions()).build());
            welfareList.put(key, list);
        }
        Optional<CorpGoods> corpGoods = corpGoodsRepository.findByCorpIdAndUserId(corp_id,user_id);
        boolean state = false;
        if (corpGoods.isPresent()) {
            state = true;
        }
        Long goodSize = corpGoodsRepository.countByCorpId(corp_id);
        return corpEntityToDto(entity, welfareList, state,goodSize, categoriesValue);
    }

    @Override
    public PageResponseDto<CorpListResponseDto, CorpAndCategoryDto> selectCorpList(Integer page, Integer size,String keyword) throws Exception {
        PageRequestDto pageRequestDto = pageModule.makePage(page,size);

        Page<Corp> entity = null;

        List<CorpAndCategoryDto> entityList = new ArrayList<>();
        List<String> categoryValue;

        if(keyword == null){
            entity = corpRepository.findAll(pageRequestDto.getPageable(Sort.by("modDate").descending()));

            for(Corp corp: entity) {
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

        }else {
            String[] keywordList =  keyword.split(",");
            List<CorpCategory> category;
            switch (keywordList.length){
                case 1:
                    category = corpCategoryRepository.findByCategory(keywordList[0]);
                    changeResponse(category, entityList);
                    break;
                case 2:
                    category = corpCategoryRepository.selectCategory(keywordList[0],keywordList[1]);
                    changeResponse(category, entityList);
                    break;
            }
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

    @Override
    public CorpResponseDto updateCorp(String corp_id, CorpRequestDto dto) throws Exception {
        Corp entity = corpRepository.findById(corp_id).orElseThrow(()->new Exception("존재하지 않는 아이디입니다."));
        entity.update(dto);

        Long result = welfareRepository.deleteByCorpId(corp_id);
        if(result==0) {
            return null;
        }

        for (String key : dto.getWelfareList().keySet()) {
            for(WelfareRequestDto welfare: dto.getWelfareList().get(key)){
                welfareRepository.save(dtoToWelfareEntity(entity, key,welfare));
            }
        }

        Map<String, List<WelfareResponseDto>> welfareList = new HashMap<>();
        for (String key : dto.getWelfareList().keySet()) {
            for(WelfareRequestDto welfare: dto.getWelfareList().get(key)){
                List<WelfareResponseDto> list = welfareList.getOrDefault(key, new ArrayList<>());
                list.add(WelfareResponseDto.builder()
                        .subTitle(welfare.getSubTitle())
                        .options(welfare.getOptions()).build());
                welfareList.put(key, list);
            }
        }

        corpCategoryRepository.deleteByCorpId(corp_id);
        for(String cate:dto.getCategory()) {
            corpCategoryRepository.save(CorpCategory.builder()
                            .corp(entity)
                            .category(cate)
                    .build());
        }

        Long goodSize = corpGoodsRepository.countByCorpId(corp_id);

        return corpEntityToDto(corpRepository.save(entity), welfareList,false, goodSize,dto.getCategory());
    }

    @Override
    public String deleteCorp(String corp_id) throws Exception {
        Corp entity = corpRepository.findById(corp_id).orElseThrow(()->new Exception("존재하지 않는 아이디 입니다."));
        corpRepository.delete(entity);
        return "삭제 완료";
    }

    @Override
    public List<CorpMiniResponseDto> selectMini(CorpMiniRequestDto dto) throws Exception {
        List<CorpMiniResponseDto> allList = new ArrayList<>();
        List<CorpAndCategoryDto> joinList = new ArrayList<>();
        int limit = 0;

        Map<Corp,List<String>> map = new HashMap<>();
        for(String data: dto.getCategory()){
            List<CorpCategory> entityList = corpCategoryRepository.findByCategory(data);
            for(CorpCategory cate : entityList) {
                List<String> list = map.getOrDefault(cate.getCorp(),new ArrayList<>());
                list.add(cate.getCategory());
                map.put(cate.getCorp(),list);
            }
            for(Corp key: map.keySet()) {
                joinList.add(CorpAndCategoryDto.builder()
                                .corp(key)
                                .category(map.get(key))
                        .build());
            }
        }
        Collections.shuffle(joinList);
        for(CorpAndCategoryDto entity: joinList) {
            if(limit>9){
                return allList;
            }
            limit++;
            allList.add(entityToMiniDto(entity.getCorp(),corpGoodsRepository.countByCorpId(entity.getCorp().getId())));
        }
        return allList;
    }

    @Override
    public String updateGoods(GoodsRequestDto dto) throws Exception{
        corpRepository.findById(dto.getCorp_id()).orElseThrow(()->new Exception("존재하지 않는 corp_id 입니다."));
        Optional<CorpGoods> value = corpGoodsRepository.findByCorpIdAndUserId(dto.getCorp_id(),dto.getUser_id());
        if(value.isPresent()){
            corpGoodsRepository.deleteByCorpIdAndUserId(dto.getCorp_id(), dto.getUser_id());
            return "좋아요 취소";
        }
        corpGoodsRepository.save(
                CorpGoods.builder()
                        .corp(corpRepository.findById(dto.getCorp_id()).get())
                        .user(bokjipsUserRepository.findById(dto.getUser_id()).get())
                        .build()
        );
        return "좋아요 등록";
    }

    @Override
    public PageResponseDto<CorpListResponseDto, CorpAndCategoryDto> selectGoodList(String user_id, Integer page, Integer size) {
        PageRequestDto pageRequestDto = pageModule.makePage(page, size);

        List<Corp> goodList = corpGoodsRepository.findByUserId(user_id).stream().map(entity -> entity.getCorp()).collect(Collectors.toList());
        List<CorpAndCategoryDto> entityList = new ArrayList<>();
        for (Corp corp : goodList) {
            List<CorpCategory> list = corpCategoryRepository.findByCorpId(corp.getId());
            List<String> category = new ArrayList<>();
            for (CorpCategory cate : list) {
                category.add(cate.getCategory());
            }
            entityList.add(CorpAndCategoryDto.builder()
                    .corp(corp)
                    .category(category)
                    .build());
        }

        Page<CorpAndCategoryDto> pageList = new PageImpl<>(entityList.subList(size * page - size, size * page), pageRequestDto.getPageable(Sort.by("modDate").descending()), entityList.size());

        Function<CorpAndCategoryDto, CorpListResponseDto> fn = (data -> corpPageToDto(data, corpGoodsRepository.countByCorpId(data.getCorp().getId())));


        PageResponseDto<CorpListResponseDto, CorpAndCategoryDto> pageResponseDto = new PageResponseDto<>(pageList, fn);

        return pageResponseDto;
    }

    public void changeResponse(List<CorpCategory> category,List<CorpAndCategoryDto> entityList) {
        List<String> categoryValue;
        for(CorpCategory corp:category) {

            categoryValue = new ArrayList<>();
            List<CorpCategory> priCategory = corpCategoryRepository.findByCorpId(corp.getCorp().getId());

            for(CorpCategory cate2: priCategory) {
                categoryValue.add(cate2.getCategory());
            }

            entityList.add(CorpAndCategoryDto.builder()
                    .corp(corp.getCorp())
                    .category(categoryValue)
                    .build());
        }
    }
}
