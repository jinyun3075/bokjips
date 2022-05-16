package com.bokjips.server.domain.corp.entity;

import com.bokjips.server.util.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Corp extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String site;

    @Column(nullable = false)
    private String career;

    @ElementCollection
    @CollectionTable(name = "category")
    private List<String> category;

    @Column(nullable = false)
    private boolean stock;

    private Long good;

    private String image;
}