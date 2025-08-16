package com.project.argoss.aplication.dto;

import com.project.argoss.domain.enums.Saude;
import com.project.argoss.domain.enums.Sexo;
import com.project.argoss.domain.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;

public record AlunoResponse(Long id, String nome, String matricula, Saude tdh, Saude tea, String foto, Sexo sexo, String Nometurma, LocalDate data_criacao, Status status) {

}


