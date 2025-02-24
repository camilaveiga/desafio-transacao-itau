package com.example.transacao_api.service;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.transacao_api.dto.TransacaoDTO;

@Service
public class TransacaoService {

    private final List<TransacaoDTO> transacoes = new ArrayList<>();

    public void adicionarTransacao (TransacaoDTO transacaoDTO){
        if (transacaoDTO.valor() < 0){
            throw new IllegalArgumentException("Valor nao pode ser negativo.");
        }
        if (transacaoDTO.dataHora().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("Data e hora não pode ser no futuro.");
        }
        
        transacoes.add(transacaoDTO);
    }

    public void deletarTransacao(){
        transacoes.clear();
    }

    public List<TransacaoDTO> buscarTransacoes(Integer periodo){

        OffsetDateTime dataDateTime = OffsetDateTime.now().minusSeconds(periodo);
        return transacoes.stream()
        .filter(transacao -> transacao.dataHora().isAfter(dataDateTime))
        .toList();
    }

}
