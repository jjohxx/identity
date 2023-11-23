package edu.umss.fcyt.posgrado.identity.repositories;

import edu.umss.fcyt.posgrado.identity.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByRoleIdAndUserId(Long roleId, Long userId);

}
