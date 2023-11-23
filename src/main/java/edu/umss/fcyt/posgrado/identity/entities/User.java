package edu.umss.fcyt.posgrado.identity.entities;

import edu.umss.fcyt.posgrado.identity.utils.SummarizedField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@Where(clause = "deleted = false")
public class User extends EntityID<Long> {

    @Column(length = 150, nullable = false)
    private String username;

    @Column(length = 150, nullable = false)
    private String password;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "deleted")
    private boolean deleted = false;

    @SummarizedField
    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;

    @SummarizedField
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

}
