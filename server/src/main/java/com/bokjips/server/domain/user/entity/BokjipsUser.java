package com.bokjips.server.domain.user.entity;

import com.bokjips.server.util.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BokjipsUser extends BaseEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 300, nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet =new HashSet<>();
    public void addUserRole(UserRole userRole) {
        roleSet.add(userRole);
    }
}