package edu.umss.fcyt.posgrado.identity.services.mapper;

import edu.umss.fcyt.posgrado.identity.dto.UserDTO;
import edu.umss.fcyt.posgrado.identity.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements CustomMapper<UserDTO, User>{

    @Override
    public UserDTO toDto(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getUsername());
        userDTO.setPassword(entity.getPassword());
        userDTO.setEmail(entity.getEmail());
        userDTO.setCreatedAt(entity.getCreatedAt());
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    }
}
