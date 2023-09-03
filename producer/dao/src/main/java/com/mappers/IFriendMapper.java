package com.mappers;

import com.dto.FriendDTO;
import com.models.Friend;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFriendMapper {

    FriendDTO toDTO(Friend friend);

    List<FriendDTO> toDTOs(List<Friend> friends);

    Friend unDTO(FriendDTO friendDTO);

    List<Friend> unDTOs(List<FriendDTO> friendDTOs);
}
