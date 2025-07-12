package com.project.argoss.domain.mapper;


import com.project.argoss.aplication.dto.TurmaRequest;
import com.project.argoss.aplication.dto.TurmaResponse;
import com.project.argoss.domain.entity.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public Turma toEntity(TurmaRequest request){
        Turma turma = new Turma();
        turma.setNome(request.nome());

        return turma;
    }


    public TurmaResponse toResponse(Turma turma){
        TurmaResponse response = new TurmaResponse(
                turma.getId(), turma.getNome(), turma.getDataCriacao()
        );
        return response;
    }

    public Turma toUpdate(Turma turma, TurmaRequest request){
        turma.setNome(request.nome());

        return turma;
    }
}
