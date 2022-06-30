package com.bokjips.server.domain.welfare.entity;

import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.welfare.dto.WelfareRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Welfare {
    @Id
    @Column(nullable = false)
    private String id;

    private String title;

    private String subtitle;

    @Column(length = 500)
    private String options;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Corp corp;
}