package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.*;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.entity.CorpGoods;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class CorpServiceImpl implements CorpService {

    private final PageModule pageModule;
    private final CorpRepository corpRepository;
    private final WelfareRepository welfareRepository;
    private final CorpGoodsRepository corpGoodsRepository;
    private final BokjipsUserRepository bokjipsUserRepository;

    @Override
    public CorpResponseDto insertCorp(CorpRequestDto dto) throws Exception {

        Corp entity = corpRepository.save(dtoToCorpEntity(dto));

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

        return corpEntityToDto(entity, welfareList, false, 0l);
    }

    @Override
    public CorpResponseDto selectCorp(String corp_id, String user_id) throws Exception {
        Corp entity = corpRepository.findById(corp_id).orElseThrow(()->new Exception("존재하지않는 아이디입니다."));
        List<Welfare> welfareListEntity = welfareRepository.findByCorpId(corp_id);
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
        return corpEntityToDto(entity, welfareList, state,goodSize);
    }

    @Override
    public PageResponseDto<CorpListResponseDto, Corp> selectCorpList(Integer page, Integer size) throws Exception {
        PageRequestDto pageRequestDto = pageModule.makePage(page,size);

        Page<Corp> entity = corpRepository.findAll(pageRequestDto.getPageable(Sort.by("modDate").descending()));


        Function<Corp, CorpListResponseDto> fn = (data -> corpPageToDto(data,corpGoodsRepository.countByCorpId(data.getId())));
        PageResponseDto<CorpListResponseDto, Corp> pageResponseDto =new PageResponseDto<>(entity, fn);

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
        Long goodSize = corpGoodsRepository.countByCorpId(corp_id);

        return corpEntityToDto(corpRepository.save(entity), welfareList,false, goodSize);
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
        int limit = 0;
        for(String data: dto.getCategory()){
            List<Corp> entityList = corpRepository.findByCategory(data);
            for(Corp entity: entityList) {
                if(limit>9){
                    return allList;
                }
                limit++;
                allList.add(entityToMiniDto(entity,corpGoodsRepository.countByCorpId(entity.getId())));
            }
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

    public void selectGood(String user_id) {
//        List<Corp> test = corpRepository.selectGoodsList(user_id);
//        log.info(test);
    }
}
