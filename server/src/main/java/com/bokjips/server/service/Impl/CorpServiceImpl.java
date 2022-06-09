package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.*;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.repository.CorpRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CorpServiceImpl implements CorpService {

    private final PageModule pageModule;
    private final CorpRepository corpRepository;
    private final WelfareRepository welfareRepository;

    @Override
    public CorpResponseDto insertCorp(CorpRequestDto dto) throws Exception {

        Corp entity = corpRepository.save(dtoToCorpEntity(dto));

        for (WelfareRequestDto welfare : dto.getWelfareList()) {
            welfareRepository.save(dtoToWelfareEntity(entity, welfare));
        }

        Map<String, List<WelfareResponseDto>> welfareList = new HashMap<>();
        for (WelfareRequestDto welfare : dto.getWelfareList()) {
            String key = welfare.getTitle();
            List<WelfareResponseDto> list = welfareList.getOrDefault(key, new ArrayList<>());
            list.add(WelfareResponseDto.builder()
                    .subTitle(welfare.getSubTitle())
                    .options(welfare.getOptions()).build());
            welfareList.put(key, list);
        }

        return corpEntityToDto(entity,welfareList);
    }

    @Override
    public CorpResponseDto selectCorp(String corp_id) throws Exception {
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

        return corpEntityToDto(entity, welfareList);
    }

    @Override
    public PageResponseDto<CorpListResponseDto, Corp> selectCorpList(Integer page, Integer size) throws Exception {
        PageRequestDto pageRequestDto = pageModule.makePage(page,size);

        Page<Corp> entity = corpRepository.findAll(pageRequestDto.getPageable(Sort.by("good").descending()));


        Function<Corp, CorpListResponseDto> fn = (data -> corpPageToDto(data));
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
    public CorpResponseDto updateCorp(String Corp_id, CorpRequestDto dto) throws Exception {
        Corp entity = corpRepository.findById(Corp_id).orElseThrow(()->new Exception("존재하지 않는 아이디입니다."));
        entity.update(dto);

        Long result = welfareRepository.deleteByCorpId(Corp_id);
        if(result==0) {
            return null;
        }

        for (WelfareRequestDto welfare : dto.getWelfareList()) {
            welfareRepository.save(dtoToWelfareEntity(entity, welfare));
        }

        Map<String, List<WelfareResponseDto>> welfareList = new HashMap<>();
        for (WelfareRequestDto welfare : dto.getWelfareList()) {
            String key = welfare.getTitle();
            List<WelfareResponseDto> list = welfareList.getOrDefault(key, new ArrayList<>());
            list.add(WelfareResponseDto.builder()
                    .subTitle(welfare.getSubTitle())
                    .options(welfare.getOptions()).build());
            welfareList.put(key, list);
        }

        return corpEntityToDto(corpRepository.save(entity),welfareList);
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
                    break;
                }
                limit++;
                allList.add(entityToMiniDto(entity));
            }
            if(limit>9){
                break;
            }
        }
        return allList;
    }
}
