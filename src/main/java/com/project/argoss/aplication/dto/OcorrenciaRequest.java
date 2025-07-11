package com.project.argoss.aplication.dto;

import com.project.argoss.domain.enums.Gravidade;

public record OcorrenciaRequest(String observacao, Gravidade gravidade, Long alunoId) {
}
