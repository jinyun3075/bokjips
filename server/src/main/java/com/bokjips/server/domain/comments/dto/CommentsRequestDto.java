package com.bokjips.server.domain.comments.dto;

import lombok.Data;

@Data
public class CommentsRequestDto {
    private String title;
    private String content;
    private String user_id;
    private String corp_id;
}
