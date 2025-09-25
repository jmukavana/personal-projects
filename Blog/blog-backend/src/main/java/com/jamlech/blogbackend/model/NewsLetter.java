package com.jamlech.blogbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "newsletter_subscriptions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsLetter extends Auditable {
    @Column(unique = true,nullable = false)
    private String email;
    @Column(name = "is_subscribed")
    private Boolean isSubscribed=Boolean.TRUE;
    @Column(name = "verification_token")
    private String verificationToken;
    @Column(name = "is_verified")
    private Boolean isVerified=Boolean.FALSE;
}
