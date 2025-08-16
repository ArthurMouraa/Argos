package com.project.argoss.aplication.dto;

import com.project.argoss.domain.enums.Saude;
import com.project.argoss.domain.enums.Sexo;

import java.util.Optional;

public record AlunoRequestPatch(Optional<String> nome, Optional<String> matricula, Optional<Saude> tdh, Optional<Saude> tea, Optional<String> foto, Optional<Sexo> sexo, Optional<Long> turmaId) {
}
