package com.bokjips.server.domain.comments.entity;

import com.bokjips.server.domain.corp.entity.Corp;
import com.bokjips.server.domain.user.entity.BokjipsUser;
import com.bokjips.server.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comments extends BaseEntity {
    @Id
    @Column(nullable = false)
    private String id;

    private String title;

    private String content;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BokjipsUser bokjipsUser;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Corp corp;
}