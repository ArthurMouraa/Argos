package com.project.argoss.aplication.dto;

import com.project.argoss.domain.enums.Saude;
import com.project.argoss.domain.enums.Sexo;

public record AlunoResponse(Long id, String nome, String matricula, Saude tdh, Saude tea, String foto, Sexo sexo, String Nometurma) {

}
