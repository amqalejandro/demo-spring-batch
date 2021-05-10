package com.example.demo.controller;

import static org.mockito.Mockito.when;

import com.example.demo.domain.PersonDto;
import com.example.demo.services.PersonaService;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PersonaController.class})
@ExtendWith(SpringExtension.class)
public class PersonaControllerTestWithSpring {

    @Autowired
    private PersonaController personaController;

    @MockBean
    private PersonaService personaService;

    @Test
    public void testGetPersonas() throws Exception {
        when(this.personaService.getPersonas()).thenReturn(new ArrayList<PersonDto>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-peronas");
        MockMvcBuilders.standaloneSetup(this.personaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetPersonas2() throws Exception {
        when(this.personaService.getPersonas()).thenReturn(new ArrayList<PersonDto>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/get-peronas");
        getResult.contentType("");
        MockMvcBuilders.standaloneSetup(this.personaController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }
}

