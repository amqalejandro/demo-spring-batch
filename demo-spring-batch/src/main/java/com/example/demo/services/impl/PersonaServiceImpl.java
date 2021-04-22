package com.example.demo.services.impl;

import com.example.demo.domain.PersonDto;
import com.example.demo.domain.mapper.PersonaMapper;
import com.example.demo.persistence.PersonEntityRepository;
import com.example.demo.services.PersonaService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class PersonaServiceImpl implements PersonaService {

  @Autowired private JobLauncher jobLauncher;
  @Autowired private Job importUserJob;
  @Autowired private PersonEntityRepository personRepository;

  @Override
  public String uploadFile(MultipartFile file) {

    try {
      ///String path = new ClassPathResource("tmpuploads/").getURL().getPath();//it's assumed you have a folder called tmpuploads in the resources folder
      File fileToImport = new File(file.getOriginalFilename());
      OutputStream outputStream = null;
      outputStream = new FileOutputStream(fileToImport);
      IOUtils.copy(file.getInputStream(), outputStream);
      outputStream.flush();

      //Launch the Batch Job
      JobParametersBuilder builder = new JobParametersBuilder();
      builder.addDate("date", new Date());
      builder.addString("fullPathFileName", fileToImport.getAbsolutePath());
      JobExecution jobExecution = jobLauncher.run(importUserJob,builder.toJobParameters());

    } catch (IOException | JobInstanceAlreadyCompleteException | JobRestartException
            | JobParametersInvalidException | JobExecutionAlreadyRunningException e) {
      e.printStackTrace();
    }
    return "ok";
  }

  @Override
  public List<PersonDto> getPersonas() {
    return PersonaMapper.INSTANCE.map(personRepository.findAll());
  }
}
