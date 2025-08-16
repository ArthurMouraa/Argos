package com.project.argoss.aplication.dto;

import com.project.argoss.domain.enums.Gravidade;

import java.util.Optional;

public record OcorrenciaRequestPatch(Optional<String> observacao, Optional<Gravidade> gravidade) {
}
