package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.domain.corp.dto.CorpResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.repository.CorpRepository;
import com.bokjips.server.service.CorpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CorpServiceImpl implements CorpService {

    private final CorpRepository corpRepository;

    @Override
    public CorpResponseDto insertCorp(CorpRequestDto dto) {
        Corp entity = corpRepository.save(dtoToEntity(dto));
        return entityToDto(entity);
    }

    @Override
    public CorpResponseDto selectCorp(UUID corp_id) {
        Optional<Corp> entity = corpRepository.findById(corp_id);
        log.info(entity);
        if(!entity.isPresent()) {
            log.info("error");
            return null;
        }
        return entityToDto(entity.get());
    }
}
