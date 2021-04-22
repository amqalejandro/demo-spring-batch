package com.example.demo.batch.steps;

import com.example.demo.domain.PersonDto;
import com.example.demo.domain.entity.PersonEntity;
import com.example.demo.domain.mapper.PersonaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<PersonDto, PersonEntity> {



  @Override
  public PersonEntity process(final PersonDto personDto) throws Exception {
    return PersonaMapper.INSTANCE.PersonDtoToPersonEntity(personDto);
  }

}
