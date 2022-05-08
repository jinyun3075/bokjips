package com.bokjips.server.domain.corp.repository;

import com.bokjips.server.domain.corp.entity.Corp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CorpRepository extends JpaRepository<Corp, UUID> {
}