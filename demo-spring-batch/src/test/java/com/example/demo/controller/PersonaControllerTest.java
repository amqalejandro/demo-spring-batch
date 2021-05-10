package com.example.demo.controller;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import com.example.demo.domain.PersonDto;
import com.example.demo.services.PersonaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
public class PersonaControllerTest {



  @Mock
  PersonaService loadFileService;



   @InjectMocks
   PersonaController personaController ;


  @Mock
  private ArrayList<PersonDto> mockArrayList;


  MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

  @Before
  public void init(){


  }

  @Test
  public void createTest() throws IOException {

    final String valorEsperado="OK";
    when(loadFileService.uploadFile(file)).thenReturn("OK");
    final String respuesta=personaController.create(file);

    assertNotNull(respuesta);
    assertEquals(respuesta,valorEsperado);

  }

    @Test
    public void getPersonasTest() throws IOException {

      mockArrayList.add(PersonDto.builder().build());
      when(loadFileService.getPersonas()).thenReturn(mockArrayList);

      final ResponseEntity<List<PersonDto>> respuesta=personaController.getPersonas();

      assertNotNull(respuesta);
      assertFalse(respuesta.getBody().isEmpty());
    }



}
