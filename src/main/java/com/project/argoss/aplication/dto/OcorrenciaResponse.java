package com.project.argoss.aplication.dto;

import com.project.argoss.domain.enums.Gravidade;

import java.time.LocalDate;
import java.time.LocalTime;

public record OcorrenciaResponse(Long id, String observacao, LocalDate dataOcorrencia, LocalTime horaOcorrencia, Gravidade gravidade, String  nomeAluno, String matriculaAluno, String nomeUsuario) {
}