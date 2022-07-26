package com.bokjips.server.domain.corp.repository;

import com.bokjips.server.domain.corp.entity.Corp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CorpRepository extends JpaRepository<Corp, String> {

    List<Corp> findByCategoryAndStock(String category, boolean stock);



//    @Query("select a from Corp a, CorpGoods b where a.id = b.corp_id and b.user_id = :user")
//    List<Corp> selectGoodsList(@Param("user") String user_id);
}