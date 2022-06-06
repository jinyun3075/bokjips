package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.comments.dto.CommentsRequestDto;
import com.bokjips.server.domain.comments.dto.CommentsResponseDto;
import com.bokjips.server.domain.comments.entity.Comments;
import com.bokjips.server.domain.comments.repository.CommentsRepository;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.corp.repository.CorpRepository;
import com.bokjips.server.domain.user.entity.BokjipsUser;
import com.bokjips.server.domain.user.repository.BokjipsUserRepository;
import com.bokjips.server.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CorpRepository corpRepository;
    private final BokjipsUserRepository bokjipsUserRepository;

    @Override
    public CommentsResponseDto insertComments(CommentsRequestDto dto) throws Exception {
        Corp corpEntity = corpRepository.findById(dto.getCorp_id()).orElseThrow(() -> new Exception("존재하지 않는 회사입니다."));

        BokjipsUser userEntity = bokjipsUserRepository.findById(dto.getUser_id()).orElseThrow(() -> new Exception("존재하지 않는 회원입니다."));

        Comments comments = commentsRepository.save(dtoToEntity(dto, corpEntity, userEntity));
        return entityToDto(comments);
    }
}
