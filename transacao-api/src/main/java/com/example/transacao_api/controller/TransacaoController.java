package com.example.transacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transacao_api.dto.TransacaoDTO;
import com.example.transacao_api.service.TransacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
  
    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> adicionarTransacao(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        transacaoService.adicionarTransacao(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deletarTransacao(){
        transacaoService.deletarTransacao();
        return ResponseEntity.ok().build();
    }


}
