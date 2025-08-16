package com.project.argoss.domain.mapper;


import com.project.argoss.aplication.dto.AlunoRequest;
import com.project.argoss.aplication.dto.AlunoRequestPatch;
import com.project.argoss.aplication.dto.AlunoResponse;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Turma;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequest dto, Turma turma){
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setMatricula(dto.matricula());
        aluno.setTdh(dto.tdh());
        aluno.setTea(dto.tea());
        aluno.setFoto(dto.foto());
        aluno.setSexo(dto.sexo());
        aluno.setTurma(turma);

        return aluno;
    }



    public AlunoResponse toResponse(Aluno aluno){
        String nomeTurma = Optional.ofNullable(aluno.getTurma())
                .map(Turma::getNome)
                .orElse(null);

        AlunoResponse alunoResponse = new AlunoResponse(
                aluno.getId(),aluno.getNome(), aluno.getMatricula(),
                aluno.getTdh(), aluno.getTea(), aluno.getFoto(), aluno.getSexo(), nomeTurma, aluno.getDataAluno(), aluno.getStatus()
        );

        return  alunoResponse;
    }

    public Page<AlunoResponse> toResponsePageable(Page<Aluno> alunos){

       return alunos.map(aluno -> new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getMatricula(), aluno.getTdh(), aluno.getTea(), aluno.getFoto(), aluno.getSexo(), aluno.getTurma().getNome(), aluno.getDataAluno(), aluno.getStatus()));
    }



    public Aluno toUpdate(Aluno aluno, AlunoRequestPatch alunoRequest, Turma turma){

        alunoRequest.nome().ifPresent(aluno::setNome);
        alunoRequest.matricula().ifPresent(aluno::setMatricula);
        alunoRequest.tdh().ifPresent(aluno::setTdh);
        alunoRequest.tea().ifPresent(aluno::setTea);
        alunoRequest.foto().ifPresent(aluno::setFoto);
        alunoRequest.sexo().ifPresent(aluno::setSexo);
        if (turma != null) aluno.setTurma(turma);
        return aluno;
    }

}