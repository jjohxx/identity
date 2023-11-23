package edu.umss.fcyt.posgrado.identity.repositories;

import edu.umss.fcyt.posgrado.identity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
