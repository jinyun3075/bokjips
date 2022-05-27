package com.bokjips.server.domain.user.repository;

import com.bokjips.server.domain.user.entity.BokjipsUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BokjipsUserRepository extends JpaRepository<BokjipsUser,String> {

    @EntityGraph(attributePaths = {"roleSet"},type = EntityGraph.EntityGraphType.LOAD)
    Optional<BokjipsUser> findByEmail(String email);
}
