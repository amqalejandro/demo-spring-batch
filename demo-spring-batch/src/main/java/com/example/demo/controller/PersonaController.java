package com.example.demo.controller;

import com.example.demo.domain.PersonDto;
import com.example.demo.services.PersonaService;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PersonaController {


  @Autowired
  private PersonaService pploadFileService;

  @RequestMapping(value="/import/file", method= RequestMethod.POST)
  public String create(@RequestParam("file") MultipartFile multipartFile) throws IOException {
    return pploadFileService.uploadFile(multipartFile);

  }

  @RequestMapping(value="/get-peronas", method= RequestMethod.GET)
  public ResponseEntity<List<PersonDto>> getPersonas() throws IOException {
    return new ResponseEntity<>(pploadFileService.getPersonas(), HttpStatus.OK);

  }



}
