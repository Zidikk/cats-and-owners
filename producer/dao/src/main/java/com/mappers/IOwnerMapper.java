package com.mappers;

import com.dto.OwnerDTO;
import com.models.Owner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOwnerMapper {

    OwnerDTO toDTO(Owner owner);

    List<OwnerDTO> toDTOs(List<Owner> owners);

    Owner unDTO(OwnerDTO ownerDTO);

    List<Owner> unDTOs(List<OwnerDTO> ownerDTOs);
}
