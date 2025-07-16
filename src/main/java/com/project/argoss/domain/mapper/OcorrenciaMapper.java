package com.project.argoss.domain.mapper;


import com.project.argoss.aplication.dto.OcorrenciaRequest;
import com.project.argoss.aplication.dto.OcorrenciaResponse;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Ocorrencia;
import com.project.argoss.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class OcorrenciaMapper {


    public Ocorrencia toEntity(OcorrenciaRequest request, Usuario usuario, Aluno aluno){

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setUsuario(usuario);
        ocorrencia.setAluno(aluno);
        ocorrencia.setGravidade(request.gravidade());
        ocorrencia.setObservacao(request.observacao());

        return ocorrencia;
    }

    public OcorrenciaResponse toResponse(Ocorrencia ocorrencia){
        OcorrenciaResponse response = new OcorrenciaResponse(
                ocorrencia.getId(),
                ocorrencia.getObservacao(),
                ocorrencia.getDataOcorrencia(),
                ocorrencia.getHoraOcorrencia(),
                ocorrencia.getGravidade(),
                ocorrencia.getAluno().getNome(),
                ocorrencia.getAluno().getMatricula(),
                ocorrencia.getUsuario().getNome()
        );

        return response;
    }


    public Ocorrencia toUpdate(OcorrenciaRequest request, Ocorrencia ocorrencia, Aluno aluno){
        Long alunoId = ocorrencia.getAluno().getId();

        ocorrencia.setObservacao(request.observacao());
        ocorrencia.setAluno(aluno);
        ocorrencia.setGravidade(request.gravidade());

        return ocorrencia;
    }

}
