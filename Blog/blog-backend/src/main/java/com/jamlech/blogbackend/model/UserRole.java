package com.jamlech.blogbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends Auditable {
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name",nullable = false,unique = true)
    private RoleName roleName;
    @Column(name = "role_description",columnDefinition = "TEXT")
    private String roleDescription;

}
