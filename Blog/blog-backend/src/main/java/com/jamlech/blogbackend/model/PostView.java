package com.jamlech.blogbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_views")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostView extends Auditable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;
}
