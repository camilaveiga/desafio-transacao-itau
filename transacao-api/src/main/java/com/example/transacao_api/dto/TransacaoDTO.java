package com.example.transacao_api.dto;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;

public record TransacaoDTO (
    @NotNull Double valor, 
    @NotNull OffsetDateTime dataHora) {
   
}
