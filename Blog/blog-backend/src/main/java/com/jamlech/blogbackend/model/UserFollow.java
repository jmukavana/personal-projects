package com.jamlech.blogbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_follows",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id","following_id"})
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFollow extends Auditable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private User following;
}
