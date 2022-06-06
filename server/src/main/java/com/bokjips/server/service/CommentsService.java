package com.bokjips.server.service;

import com.bokjips.server.domain.comments.dto.CommentsRequestDto;
import com.bokjips.server.domain.comments.dto.CommentsResponseDto;
import com.bokjips.server.domain.comments.entity.Comments;
import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.user.entity.BokjipsUser;

import java.util.UUID;

public interface CommentsService {
    CommentsResponseDto insertComments(CommentsRequestDto dto) throws Exception;

    default Comments dtoToEntity(CommentsRequestDto dto, Corp corpEntity, BokjipsUser userEntity) {
        return Comments.builder()
                .id(UUID.randomUUID().toString())
                .title(dto.getTitle())
                .content(dto.getContent())
                .corp(corpEntity)
                .bokjipsUser(userEntity)
                .build();
    }

    default CommentsResponseDto entityToDto(Comments comments) {
        return CommentsResponseDto.builder()
                .comments_id(comments.getId())
                .title(comments.getTitle())
                .content(comments.getContent())
                .writer(comments.getBokjipsUser())
                .modDate(comments.getModDate())
                .regDate(comments.getRegDate())
                .build();
    }
}
