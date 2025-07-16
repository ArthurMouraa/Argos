package com.project.argoss.aplication.service;


import com.project.argoss.aplication.dto.OcorrenciaRequest;
import com.project.argoss.aplication.dto.OcorrenciaResponse;
import com.project.argoss.apresentation.exception.RecursoNaoEncontradoException;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Ocorrencia;
import com.project.argoss.domain.entity.Usuario;
import com.project.argoss.domain.mapper.OcorrenciaMapper;
import com.project.argoss.domain.repository.AlunoRepository;
import com.project.argoss.domain.repository.OcorrenciaRepository;
import com.project.argoss.domain.repository.UsuarioRepository;
import com.project.argoss.infrastructure.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OcorrenciaService {

    @Autowired
    private final OcorrenciaRepository ocorrenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AlunoRepository alunoRepository;
    private final OcorrenciaMapper ocorrenciaMapper;


    public OcorrenciaResponse criar(OcorrenciaRequest ocorrenciaRequest, HttpServletRequest httpServletRequest) {

        Usuario usuario = usuarioRepository.findById(jwtService.idUsuarioLogado(httpServletRequest)).orElseThrow(
                () -> new RecursoNaoEncontradoException("Erro ao buscar usuario")
        );

        Aluno aluno = alunoRepository.findById(ocorrenciaRequest.alunoId()).orElseThrow(() -> new RecursoNaoEncontradoException("Aluno nao encontrado"));

        Ocorrencia ocorrencia = ocorrenciaMapper.toEntity(ocorrenciaRequest, usuario, aluno);


        ocorrenciaRepository.save(ocorrencia);


        return ocorrenciaMapper.toResponse(ocorrencia);
    }


    public List<OcorrenciaResponse> ocorrenciaAluno(Long id) {

        List<OcorrenciaResponse> ocorrencias = ocorrenciaRepository.findByAlunoId(id).stream().map(ocorrencia -> ocorrenciaMapper.toResponse(ocorrencia)).toList();

        return ocorrencias;
    }


    public OcorrenciaResponse atualizar(Long id, OcorrenciaRequest ocorrenciaRequest) {

        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Ocorrencia não existe"));

        Aluno aluno = alunoRepository.findById(ocorrenciaRequest.alunoId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("Aluno não encontrado")
        );

        Ocorrencia atualizacao = ocorrenciaMapper.toUpdate(ocorrenciaRequest, ocorrencia, aluno);

        ocorrenciaRepository.save(atualizacao);

        return ocorrenciaMapper.toResponse(atualizacao);
    }


    public void deletar(Long id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Ocorrencia nao encontrada")
        );

        ocorrenciaRepository.delete(ocorrencia);
    }

}


