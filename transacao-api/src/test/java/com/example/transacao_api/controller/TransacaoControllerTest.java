package com.example.transacao_api.controller;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.example.transacao_api.dto.TransacaoDTO;
import com.example.transacao_api.service.TransacaoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
    }

    @Test
    void testAdicionarTransacao() throws Exception {
    TransacaoDTO transacao = new TransacaoDTO(123.45, OffsetDateTime.parse("2025-02-25T11:30:15.123-03:00"));
    
    doNothing().when(transacaoService).adicionarTransacao(any(TransacaoDTO.class));

    mockMvc.perform(post("/transacao")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(transacao)))
        .andExpect(status().isCreated());
    }

    @Test
    void testDeleteTransacao() throws Exception {
        
        doNothing().when(transacaoService).deletarTransacao();
        mockMvc.perform(delete("/transacao"))
            .andExpect(status().isOk());
    }
}
