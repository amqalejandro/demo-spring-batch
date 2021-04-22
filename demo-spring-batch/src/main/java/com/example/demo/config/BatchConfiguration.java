package com.example.demo.config;

import com.example.demo.batch.steps.PersonItemProcessor;
import com.example.demo.batch.steps.PersonItemWrite;
import com.example.demo.domain.PersonDto;
import com.example.demo.domain.entity.PersonEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing

public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Bean
  @StepScope
  public FlatFileItemReader<PersonDto> importReader(@Value("#{jobParameters[fullPathFileName]}")
      String pathToFile) {


    return new FlatFileItemReaderBuilder<PersonDto>()
        .name("personItemReader")
        .resource(new FileSystemResource(pathToFile))
        .delimited()
        .names(new String[]{"nombre", "apellidos"})
        .fieldSetMapper(new BeanWrapperFieldSetMapper<PersonDto>() {{
          setTargetType(PersonDto.class);
        }})
        .build();

  }

  @Bean
  public PersonItemProcessor processor() {
    return new PersonItemProcessor();
  }

  @Bean
  public PersonItemWrite writer() {
    return new PersonItemWrite();
  }

  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener
      ,@Qualifier("importReader") ItemReader<PersonDto> importReader) {

    return jobBuilderFactory
            .get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1(importReader))
            .end()
            .build();
  }



  @Bean
  public Step step1(
      ItemReader<PersonDto> importReader) {
    return stepBuilderFactory
        .get("step1")
        .<PersonDto, PersonEntity>chunk(10)
        .reader(importReader)
        .processor(processor())
        .writer(writer())
        .build();
  }





}
