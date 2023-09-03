package com.mappers;

import com.dto.UserDTO;
import com.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(List<User> users);

    User unDTO(UserDTO userDTO);

    List<User> unDTOs(List<UserDTO> userDTOs);
}
