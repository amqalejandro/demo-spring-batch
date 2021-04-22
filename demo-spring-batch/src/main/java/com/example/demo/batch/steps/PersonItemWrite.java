package com.example.demo.batch.steps;

import com.example.demo.domain.entity.PersonEntity;
import com.example.demo.persistence.PersonEntityRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonItemWrite implements ItemWriter<PersonEntity>{

  @Autowired
  PersonEntityRepository personRepository;

  @Override
  public void write(List<? extends PersonEntity> list) throws Exception {
    personRepository.saveAll(list);
  }
}
