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
import com.bokjips.server.util.dto.PageRequestDto;
import com.bokjips.server.util.dto.PageResponseDto;
import com.bokjips.server.util.module.PageModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CorpRepository corpRepository;
    private final BokjipsUserRepository bokjipsUserRepository;

    private final PageModule pageModule;

    @Override
    public CommentsResponseDto insertComments(CommentsRequestDto dto) throws Exception {
        Corp corpEntity = corpRepository.findById(dto.getCorp_id()).orElseThrow(() -> new Exception("존재하지 않는 회사입니다."));

        BokjipsUser userEntity = bokjipsUserRepository.findById(dto.getUser_id()).orElseThrow(() -> new Exception("존재하지 않는 회원입니다."));

        Comments comments = commentsRepository.save(dtoToEntity(dto, corpEntity, userEntity));
        return entityToDto(comments);
    }

    @Override
    public PageResponseDto<CommentsResponseDto ,Comments> selectListComments(String corp_id, Integer page, Integer size) throws Exception {
        PageRequestDto pageRequestDto = pageModule.makePage(page,size);

        Page<Comments> list = commentsRepository.findByCorpId(corp_id,pageRequestDto.getPageable(Sort.by("modDate").descending()));

        Function<Comments, CommentsResponseDto> fn = (data->entityToDto(data));

        return new PageResponseDto<>(list,fn);
    }
}
