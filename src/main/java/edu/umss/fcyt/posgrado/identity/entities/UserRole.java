package edu.umss.fcyt.posgrado.identity.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_role")
public class UserRole extends EntityID<Long> {

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDate createdAt;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;
}
