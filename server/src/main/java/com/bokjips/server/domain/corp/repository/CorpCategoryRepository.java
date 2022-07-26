package com.bokjips.server.domain.corp.repository;

import com.bokjips.server.domain.corp.entity.CorpCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CorpCategoryRepository extends JpaRepository<CorpCategory, Long> {

    List<CorpCategory> findByCorpId(String corp_id);
    Page<CorpCategory> findByUserId(String user_id,Pageable pageable);
    Page<CorpCategory> findByCategory(String category, Pageable pageable);
    List<CorpCategory> findByCategory(String category);

    @Transactional
    Long deleteByCorpId(String corp_id);
}