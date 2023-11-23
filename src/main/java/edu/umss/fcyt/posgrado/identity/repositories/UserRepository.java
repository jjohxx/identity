package edu.umss.fcyt.posgrado.identity.repositories;

import edu.umss.fcyt.posgrado.identity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    Set<User> findUsersByRoleName(@Param("roleName") String roleName);
}
