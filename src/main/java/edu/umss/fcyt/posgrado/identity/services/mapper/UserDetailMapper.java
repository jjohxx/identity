package edu.umss.fcyt.posgrado.identity.services.mapper;

import edu.umss.fcyt.posgrado.identity.dto.UserDetailDTO;
import edu.umss.fcyt.posgrado.identity.entities.UserDetail;
import org.springframework.stereotype.Component;

@Component
public class UserDetailMapper implements CustomMapper<UserDetailDTO, UserDetail> {

    @Override
    public UserDetailDTO toDto(UserDetail entity) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(entity.getId());
        userDetailDTO.setFirstName(entity.getFirstName());
        userDetailDTO.setLastName(entity.getLastName());
        userDetailDTO.setAge(entity.getAge());
        userDetailDTO.setBirthDay(entity.getBirthDay());
        return userDetailDTO;
    }

    @Override
    public UserDetail toEntity(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(userDetailDTO.getId());
        userDetail.setFirstName(userDetail.getFirstName());
        userDetail.setLastName(userDetail.getLastName());
        userDetail.setAge(userDetail.getAge());
        userDetail.setBirthDay(userDetail.getBirthDay());
        return userDetail;
    }
}
