package com.example.demo.services.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.entity.PersonEntity;
import com.example.demo.persistence.PersonEntityRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonaServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class PersonaServiceImplTest {
    @MockBean
    private Job job;

    @MockBean
    private JobLauncher jobLauncher;

    @MockBean
    private PersonEntityRepository personEntityRepository;

    @MockBean
    JobParametersBuilder builder;

    @Autowired
    private PersonaServiceImpl personaServiceImpl;

    MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

    @Test
    public void testGetPersonas() {
        when(this.personEntityRepository.findAll()).thenReturn(new ArrayList<PersonEntity>());
        assertTrue(this.personaServiceImpl.getPersonas().isEmpty());
        verify(this.personEntityRepository).findAll();
    }


}

