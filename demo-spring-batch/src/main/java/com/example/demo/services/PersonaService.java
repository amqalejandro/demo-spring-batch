package com.example.demo.services;

import com.example.demo.domain.PersonDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonaService {

  String uploadFile(MultipartFile file);
  List<PersonDto> getPersonas();
}
