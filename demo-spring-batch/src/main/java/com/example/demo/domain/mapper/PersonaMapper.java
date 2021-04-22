package com.example.demo.domain.mapper;

import com.example.demo.domain.PersonDto;
import com.example.demo.domain.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonaMapper {


    PersonaMapper INSTANCE = Mappers.getMapper( PersonaMapper.class );

    PersonDto PersonaEntityToPersonDto(PersonEntity personEntity);

    @Mapping(target = "id", ignore = true)
    PersonEntity PersonDtoToPersonEntity(PersonDto personDto);

    List<PersonDto> map(List<PersonEntity> personas);


}
