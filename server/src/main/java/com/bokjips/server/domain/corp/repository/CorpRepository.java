package com.bokjips.server.domain.corp.repository;

import com.bokjips.server.domain.corp.entity.Corp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CorpRepository extends JpaRepository<Corp, String> {

    List<Corp> findByCategory(String category);

//    @Query("select a from Corp a where a.goods.user_id = :user")
//    List<Corp> selectGoodsList(@Param("user") String user_id);
}