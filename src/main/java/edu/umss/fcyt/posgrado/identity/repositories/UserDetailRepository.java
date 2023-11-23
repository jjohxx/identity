package edu.umss.fcyt.posgrado.identity.repositories;

import edu.umss.fcyt.posgrado.identity.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
