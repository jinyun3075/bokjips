package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.domain.corp.dto.CorpResponseDto;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.repository.CorpRepository;
import com.bokjips.server.service.CorpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorpServiceImpl implements CorpService {

    private final CorpRepository corpRepository;

    @Override
    public CorpResponseDto insertCorp(CorpRequestDto dto) {
        Corp entity = corpRepository.save(dtoToEntity(dto));
        return entityToDto(entity);
    }
}
