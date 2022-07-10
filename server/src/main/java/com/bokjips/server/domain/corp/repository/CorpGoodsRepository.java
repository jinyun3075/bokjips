package com.bokjips.server.domain.corp.repository;

import com.bokjips.server.domain.corp.entity.CorpGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CorpGoodsRepository extends JpaRepository<CorpGoods, Long> {
    Optional<CorpGoods> findByCorpIdAndUserId(String corp_id,String user_id);

    Long countByCorpId(String corp_id);

    @Transactional
    Long deleteByCorpIdAndUserId(String corp_id, String user_id);
}