package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.domain.corp.dto.CorpResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.repository.CorpRepository;
import com.bokjips.server.service.CorpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CorpServiceImpl implements CorpService {

    private final CorpRepository corpRepository;

    @Override
    public CorpResponseDto insertCorp(CorpRequestDto dto) throws IOException {
        Corp entity = corpRepository.save(dtoToEntity(dto));
        return entityToDto(entity);
    }

    @Override
    public CorpResponseDto selectCorp(UUID corp_id) throws IOException{
        Optional<Corp> entity = corpRepository.findById(corp_id);
        log.info(entity);
        if(!entity.isPresent()) {
            log.info("error");
            return null;
        }
        return entityToDto(entity.get());
    }

    @Override
    public CorpResponseDto updateCorp(UUID Corp_id, CorpRequestDto dto) throws IOException {
        Optional<Corp> entity = corpRepository.findById(Corp_id);
        entity.get().update(dto);
        return entityToDto(corpRepository.save(entity.get()));
    }

    @Override
    public String deleteCorp(UUID corp_id) throws IOException {
        Optional<Corp> entity = corpRepository.findById(corp_id);
        if(!entity.isPresent()) {
            log.info("error");
            return "삭제 실패";
        }
        corpRepository.delete(entity.get());
        return "삭제 완료";
    }
}
