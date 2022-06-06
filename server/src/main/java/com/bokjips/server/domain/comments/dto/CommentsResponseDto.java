package com.bokjips.server.domain.comments.dto;

import com.bokjips.server.domain.user.entity.BokjipsUser;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentsResponseDto {
    private String comments_id;
    private String title;
    private String content;
    private BokjipsUser writer;
    private final LocalDateTime regDate;
    private final LocalDateTime modDate;
}
