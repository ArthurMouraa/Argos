package com.project.argoss.aplication.dto;

import java.time.LocalDate;

public record TurmaResponse(Long id, String nome, LocalDate dataCriacao) {
}