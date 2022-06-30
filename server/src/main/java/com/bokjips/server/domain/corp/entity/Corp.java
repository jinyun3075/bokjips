package com.bokjips.server.domain.corp.entity;

import com.bokjips.server.domain.corp.dto.CorpRequestDto;
import com.bokjips.server.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.TypeDef;

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
    @Column(nullable = false)
    private String id;

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

    @ElementCollection
    @CollectionTable(name = "goods")
    private List<String> userId;

    private String image;

    public void update(CorpRequestDto dto){
        this.name = dto.getName();
        this.site = dto.getSite();
        this.career = dto.getCareer();
        this.category = dto.getCategory();
        this.stock = dto.isStock();
        this.image = dto.getImage();
    }
}