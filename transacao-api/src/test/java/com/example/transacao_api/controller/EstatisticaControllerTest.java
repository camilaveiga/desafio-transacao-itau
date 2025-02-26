package com.example.transacao_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.transacao_api.dto.EstatisticaDTO;
import com.example.transacao_api.service.EstatisticaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @InjectMocks
    private EstatisticaController estatisticaController;

    @Mock
    private EstatisticaService estatisticaService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
    }

    @Test
    void buscarEstatisticas() throws Exception{
        EstatisticaDTO estatistica = new EstatisticaDTO(2l, 100.0, 100.0, 100.0, 100.0);
        
        when(estatisticaService.apresentaEstatistica(any(Integer.class))).thenReturn(estatistica);

        mockMvc.perform(get("/estatistica")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(estatistica)))
            .andExpect(status().isOk());
    }

}
