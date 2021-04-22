package com.example.demo.domain.mapper;

import com.example.demo.domain.PersonDto;
import com.example.demo.domain.entity.PersonEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-22T17:45:43-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.jar, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonDto PersonaEntityToPersonDto(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setApellidos( personEntity.getApellidos() );
        personDto.setNombre( personEntity.getNombre() );

        return personDto;
    }

    @Override
    public PersonEntity PersonDtoToPersonEntity(PersonDto personDto) {
        if ( personDto == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setNombre( personDto.getNombre() );
        personEntity.setApellidos( personDto.getApellidos() );

        return personEntity;
    }

    @Override
    public List<PersonDto> map(List<PersonEntity> personas) {
        if ( personas == null ) {
            return null;
        }

        List<PersonDto> list = new ArrayList<PersonDto>( personas.size() );
        for ( PersonEntity personEntity : personas ) {
            list.add( PersonaEntityToPersonDto( personEntity ) );
        }

        return list;
    }
}
