package com.project.argoss.domain.mapper;


import com.project.argoss.aplication.dto.AlunoRequest;
import com.project.argoss.aplication.dto.AlunoResponse;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Turma;
import org.springframework.stereotype.Component;

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
        AlunoResponse alunoResponse = new AlunoResponse(
                aluno.getId(),aluno.getNome(), aluno.getMatricula(),
                aluno.getTdh(), aluno.getTea(), aluno.getFoto(), aluno.getSexo(), aluno.getTurma().getNome()
        );

        return  alunoResponse;
    }


    public Aluno toUpdate(Aluno aluno, AlunoRequest alunoRequest, Turma turma){
        aluno.setNome(alunoRequest.nome());
        aluno.setMatricula(alunoRequest.matricula());
        aluno.setTdh(alunoRequest.tdh());
        aluno.setTea(alunoRequest.tea());
        aluno.setFoto(alunoRequest.foto());
        aluno.setSexo(alunoRequest.sexo());
        aluno.setTurma(turma);

        return aluno;
    }

}