package com.bokjips.server.domain.comments.repository;

import com.bokjips.server.domain.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, String> {
}