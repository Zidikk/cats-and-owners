package com.mappers;

import com.dto.CatDTO;
import com.models.Cat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICatMapper {

    CatDTO toDTO(Cat cat);

    List<CatDTO> toDTOs(List<Cat> cats);

    Cat unDTO(CatDTO catDTO);

    List<Cat> unDTOs(List<CatDTO> catDTOs);
}
