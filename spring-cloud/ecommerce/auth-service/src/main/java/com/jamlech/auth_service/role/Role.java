package com.jamlech.auth_service.role;

import com.jamlech.auth_service.common.BaseEntity;
import com.jamlech.auth_service.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Role extends BaseEntity {
    @ManyToMany(mappedBy = "roles")
    List<User> users;
    private String name;
}

