package com.project.argoss.aplication.service;


import com.project.argoss.aplication.dto.AlunoRequest;
import com.project.argoss.aplication.dto.AlunoResponse;
import com.project.argoss.apresentation.exception.RecursoNaoEncontradoException;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Turma;
import com.project.argoss.domain.mapper.AlunoMapper;
import com.project.argoss.domain.repository.AlunoRepository;
import com.project.argoss.domain.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {


    @Autowired
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;
    private final TurmaRepository turmaRepository;


    public AlunoResponse criar(AlunoRequest alunoRequest) {

        Turma turma = turmaRepository.findById(alunoRequest.turmaId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("Turma não encontrada")
        );


        Aluno aluno = alunoMapper.toEntity(alunoRequest, turma);
        alunoRepository.save(aluno);

        AlunoResponse alunoResponse = alunoMapper.toResponse(aluno);

        return alunoResponse;
    }

    public Aluno buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Aluno com ID %d não encontrado".formatted(id))
        );

        return aluno;
    }


    public List<AlunoResponse> buscarPorNome(String nome) {
        List<Aluno> alunos = alunoRepository.buscarPorNome(nome);

        return alunos.stream().map(aluno ->
                alunoMapper.toResponse(aluno)).toList();
    }

    public List<AlunoResponse> listarAlunosPorTurma(Long turmaId) {

        Turma turma = turmaRepository.findById(turmaId).orElseThrow(
                () -> new RecursoNaoEncontradoException(("Turma de id %D não encontrada").formatted(turmaId))
        );

        List<AlunoResponse> alunos = alunoRepository.findByTurma(turma).stream()
                .map(aluno ->
                        alunoMapper.toResponse(aluno)).toList();

        return alunos;
    }


    public AlunoResponse atualizar(Long id, AlunoRequest alunoRequest) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Aluno com ID %d não encontrado".formatted(id))
        );

        Turma turma = turmaRepository.findById(alunoRequest.turmaId()).orElseThrow(() -> new RecursoNaoEncontradoException("Turma não existe"));

        alunoRepository.save(alunoMapper.toUpdate(aluno, alunoRequest, turma));

        return alunoMapper.toResponse(aluno);
    }

    public void deletar(Long id) {

        Aluno aluno = alunoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Aluno não encontrado")
        );

        alunoRepository.delete(aluno);
    }
}
