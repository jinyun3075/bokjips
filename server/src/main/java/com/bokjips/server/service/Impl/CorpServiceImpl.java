package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.domain.corp.dto.CorpResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.repository.CorpRepository;
import com.bokjips.server.domain.welfare.dto.WelfareRequestDto;
import com.bokjips.server.domain.welfare.dto.WelfareResponseDto;
import com.bokjips.server.domain.welfare.entity.Welfare;
import com.bokjips.server.domain.welfare.repository.WelfareRepository;
import com.bokjips.server.service.CorpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class CorpServiceImpl implements CorpService {

    private final CorpRepository corpRepository;
    private final WelfareRepository welfareRepository;

    @Override
    public CorpResponseDto insertCorp(CorpRequestDto dto) throws IOException {
        Corp entity = corpRepository.save(dtoToCorpEntity(dto));
        Map<String, List<WelfareResponseDto>> welfareList = new HashMap<>();

        for (WelfareRequestDto welfare : dto.getWelfareList()) {
            welfareRepository.save(dtoToWelfareEntity(entity, welfare));
        }

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
    public CorpResponseDto selectCorp(String corp_id) throws IOException {
        Optional<Corp> entity = corpRepository.findById(corp_id);
        if (!entity.isPresent()) {
            log.info("error");
            return null;
        }
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

        return corpEntityToDto(entity.get(), welfareList);
    }

    @Override
    public CorpResponseDto updateCorp(String Corp_id, CorpRequestDto dto) throws IOException {
        Optional<Corp> entity = corpRepository.findById(Corp_id);
        entity.get().update(dto);
//        return corpEntityToDto(corpRepository.save(entity.get()));
        return null;
    }

    @Override
    public String deleteCorp(String corp_id) throws IOException {
        Optional<Corp> entity = corpRepository.findById(corp_id);
        if (!entity.isPresent()) {
            log.info("error");
            return "삭제 실패";
        }
        corpRepository.delete(entity.get());
        return "삭제 완료";
    }
}
