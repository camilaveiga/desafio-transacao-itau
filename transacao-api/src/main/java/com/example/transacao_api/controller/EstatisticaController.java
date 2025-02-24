package com.example.transacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transacao_api.dto.EstatisticaDTO;
import com.example.transacao_api.service.EstatisticaService;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    private EstatisticaService estatisticaService;
    
    @GetMapping
    public ResponseEntity<EstatisticaDTO> buscarEstatisticas(
        @RequestParam(value = "periodo", required = false, defaultValue = "60") Integer periodo){
        EstatisticaDTO result = estatisticaService.apresentaEstatistica(periodo);
        return ResponseEntity.ok(result);
    }
}
