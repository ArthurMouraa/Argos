package com.project.argoss.aplication.service;


import com.project.argoss.aplication.dto.TurmaRequest;
import com.project.argoss.aplication.dto.TurmaResponse;
import com.project.argoss.apresentation.exception.RecursoNaoEncontradoException;
import com.project.argoss.domain.entity.Turma;
import com.project.argoss.domain.mapper.TurmaMapper;
import com.project.argoss.domain.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TurmaService {

    @Autowired
    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;




    public Turma criar(TurmaRequest turmaRequest){
        Turma turma = turmaMapper.toEntity(turmaRequest);
        turmaRepository.save(turma);

        return turma;
    }



    public TurmaResponse buscarPorId(Long id){
        Turma turma = turmaRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(("Turma de id %D não encontrada").formatted(id))
        );

        return turmaMapper.toResponse(turma);
    }




    public List<TurmaResponse> buscarTodos(){
        List<Turma> turmas = turmaRepository.findAll();

        return turmas.stream().map(
                turma ->
                        turmaMapper.toResponse(turma)).toList();
    }





    public TurmaResponse atualizar(Long id, TurmaRequest request){
        Turma turma =  turmaRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("atualizacao invalida: turma nao encontrada")
        );


        Turma atualizacao = turmaMapper.toUpdate(turma, request);
        turmaRepository.save(atualizacao);

        return turmaMapper.toResponse(atualizacao);
    }



    public void deletar(Long id){
        Turma turma =  turmaRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("atualizacao invalida: turma nao encontrada")
        );

        turmaRepository.delete(turma);
    }



}