package com.project.argoss.aplication.service;


import com.project.argoss.aplication.dto.OcorrenciaRequest;
import com.project.argoss.aplication.dto.OcorrenciaRequestPatch;
import com.project.argoss.aplication.dto.OcorrenciaResponse;
import com.project.argoss.aplication.util.OcorrenciaSpecification;
import com.project.argoss.apresentation.exception.RecursoNaoEncontradoException;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Ocorrencia;
import com.project.argoss.domain.entity.Usuario;
import com.project.argoss.domain.enums.Gravidade;
import com.project.argoss.domain.mapper.OcorrenciaMapper;
import com.project.argoss.domain.repository.AlunoRepository;
import com.project.argoss.domain.repository.OcorrenciaRepository;
import com.project.argoss.domain.repository.UsuarioRepository;
import com.project.argoss.infrastructure.config.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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


    public Page<OcorrenciaResponse> buscarOcorrenciasDoAluno(Long alunoId, LocalDate dataInicial, LocalDate dataFinal,  Pageable pageable){

        List<Specification<Ocorrencia>> specs = List.of(
                OcorrenciaSpecification.comAlunoId(alunoId),
                OcorrenciaSpecification.comDataEntre(dataInicial, dataFinal));

        Specification<Ocorrencia> finalSpec = Specification.allOf(specs);
        Page<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll(finalSpec, pageable);

        return  ocorrenciaMapper.toResponsePageable(ocorrencias);
    }


    public Page<OcorrenciaResponse> buscarTodasOcorrencias(LocalDate dataInicial, LocalDate dataFinal,String  matricula, String nomeAluno, Pageable pageable){

        List<Specification<Ocorrencia>> spec = List.of(
                OcorrenciaSpecification.comDataEntre(dataInicial, dataFinal),
                OcorrenciaSpecification.comMatriculaAluno(matricula),
                OcorrenciaSpecification.comNomeAluno(nomeAluno));

        Specification<Ocorrencia> combinaSpecifications = Specification.allOf(spec);

       Page<Ocorrencia> ocorrencias =  ocorrenciaRepository.findAll(combinaSpecifications, pageable);

        return  ocorrenciaMapper.toResponsePageable(ocorrencias);

    }

    public Page<OcorrenciaResponse> buscarMinhasOcorrencias(LocalDate dataInicial, LocalDate dataFinal, HttpServletRequest httpServletRequest, Pageable pageable){

        String usuarioId = jwtService.idUsuarioLogado(httpServletRequest);

        List<Specification<Ocorrencia>> spec = List.of(
                OcorrenciaSpecification.comUsuario(usuarioId),
                OcorrenciaSpecification.comDataEntre(dataInicial, dataFinal));

        Specification<Ocorrencia> combinaSpecifications = Specification.allOf(spec);
        Page<Ocorrencia> ocorrencias =  ocorrenciaRepository.findAll(combinaSpecifications, pageable);

        return  ocorrenciaMapper.toResponsePageable(ocorrencias);

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


    public void atualizaParcial(Long id, OcorrenciaRequestPatch request){

        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Ocorrencia não existe")
        );

        Ocorrencia ocorrenciaUpdate = ocorrenciaMapper.toUpdateParcial(request, ocorrencia);

        ocorrenciaRepository.save(ocorrenciaUpdate);
    }





}


