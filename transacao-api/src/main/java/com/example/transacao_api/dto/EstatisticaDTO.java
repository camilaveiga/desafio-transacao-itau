package com.example.transacao_api.dto;

public record EstatisticaDTO(
    Long count,
    double sum,
    double avg,
    double min,
    double max) {

}
