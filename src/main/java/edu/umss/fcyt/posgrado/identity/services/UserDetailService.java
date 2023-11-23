package edu.umss.fcyt.posgrado.identity.services;

import edu.umss.fcyt.posgrado.identity.dto.UserDetailDTO;
import edu.umss.fcyt.posgrado.identity.entities.UserDetail;
import edu.umss.fcyt.posgrado.identity.repositories.UserDetailRepository;
import edu.umss.fcyt.posgrado.identity.services.mapper.CustomMapper;
import edu.umss.fcyt.posgrado.identity.services.mapper.UserDetailMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService extends EntityService<UserDetailDTO, UserDetail, Long> {

    private final UserDetailRepository userDetailRepository;
    private final UserDetailMapper userDetailMapper;

    @Override
    public Class<UserDetail> getEntityClass() {
        return UserDetail.class;
    }

    @Override
    public JpaRepository<UserDetail, Long> getRepository() {
        return userDetailRepository;
    }

    @Override
    public CustomMapper<UserDetailDTO, UserDetail> getMapper() {
        return userDetailMapper;
    }
}
