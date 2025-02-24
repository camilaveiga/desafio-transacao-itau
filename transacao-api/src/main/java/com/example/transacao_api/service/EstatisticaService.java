package com.example.transacao_api.service;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.transacao_api.dto.EstatisticaDTO;
import com.example.transacao_api.dto.TransacaoDTO;

@Service
public class EstatisticaService {

    @Autowired
    TransacaoService transacaoService;

    public EstatisticaDTO apresentaEstatistica(Integer periodo){

        List<TransacaoDTO> transacoes = transacaoService.buscarTransacoes(periodo);

        DoubleSummaryStatistics stats = transacoes.stream().mapToDouble(TransacaoDTO :: valor).summaryStatistics();
     
        return new EstatisticaDTO(
            stats.getCount(),
            stats.getSum(),
            stats.getAverage(),
            stats.getMin(),
            stats.getMax()
        );
    }

}
