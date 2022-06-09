package com.bokjips.server.domain.comments.repository;

import com.bokjips.server.domain.comments.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommentsRepository extends JpaRepository<Comments, String> {
    Page<Comments> findByCorpId(String corp_id, Pageable pageable);
}