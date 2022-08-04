package com.bokjips.server.domain.corp.repository;

import com.bokjips.server.domain.corp.entity.CorpCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CorpCategoryRepository extends JpaRepository<CorpCategory, Long> {

    List<CorpCategory> findByCorpId(String corp_id);
    List<CorpCategory> findByCategory(String category);

    @Query("select a from CorpCategory a where a.category=:category1 OR a.category=:category2 group by corp_id HAVING count(corp_id) > 1")
    List<CorpCategory> selectCategory(@Param("category1") String category1, @Param("category2")String category2);

    @Transactional
    Long deleteByCorpId(String corp_id);
}