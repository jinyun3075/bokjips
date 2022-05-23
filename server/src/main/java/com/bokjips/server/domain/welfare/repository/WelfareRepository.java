package com.bokjips.server.domain.welfare.repository;

import com.bokjips.server.domain.welfare.entity.Welfare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WelfareRepository extends JpaRepository<Welfare, UUID> {
}