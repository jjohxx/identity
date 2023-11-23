package edu.umss.fcyt.posgrado.identity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Role extends EntityID<Long> {

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 450)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<User> users;
}
