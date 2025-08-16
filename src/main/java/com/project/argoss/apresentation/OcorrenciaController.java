package com.project.argoss.apresentation;


import com.project.argoss.aplication.dto.AlunoResponse;
import com.project.argoss.aplication.dto.OcorrenciaRequest;
import com.project.argoss.aplication.dto.OcorrenciaRequestPatch;
import com.project.argoss.aplication.dto.OcorrenciaResponse;
import com.project.argoss.aplication.service.OcorrenciaService;
import com.project.argoss.aplication.util.AlunoSpecification;
import com.project.argoss.aplication.util.OcorrenciaSpecification;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Ocorrencia;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private final OcorrenciaService ocorrenciaService;


    @PostMapping("/criar")
    @Operation(
            summary = "Cria uma nova ocorrência",
            description = "Registra uma nova ocorrência associada a um aluno e lançada pelo professor logado(Usuário)."
    )
    public ResponseEntity criar(@RequestBody OcorrenciaRequest request, HttpServletRequest httpServletRequest){

        OcorrenciaResponse ocorrencia = ocorrenciaService.criar(request, httpServletRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrencia);
    }


    @GetMapping("/alunos/{id}")
    @Operation(
            summary = "Consulta ocorrências de um aluno",
            description = "Retorna uma lista paginada das ocorrências registradas  para um determinado aluno. É possível filtrar por data de ocorrência."
    )
    public ResponseEntity buscarPorAluno(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dataOcorrencia") String sortBy,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
            ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

       Page<OcorrenciaResponse>  oc = ocorrenciaService.
                buscarOcorrenciasDoAluno(id, dataInicial, dataFinal, pageable);

       return ResponseEntity.ok(oc);
    }


    @PutMapping("/atualizar/{id}")
    @Operation(
            summary = "Atualiza uma ocorrência",
            description = "Permite ao usuário do sistema atualizar uma ocorrência realizada por ele"
    )
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody OcorrenciaRequest request){
        OcorrenciaResponse response =  ocorrenciaService.atualizar(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }



    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deleta uma ocorrencia",
            description = "Permite deletar uma ocorrencia registrada, recbe o Id da ocorrencia como parâmetro"
    )
    public ResponseEntity deletar(@PathVariable Long id){
        ocorrenciaService.deletar(id);

        return ResponseEntity.status(HttpStatus.OK).body("ocorrencia deletada");
    }



    @GetMapping("/todos")
    @Operation(
            summary = "Busca todas ocorrências",
            description = "Permite ao usuário do sistema buscar varias ocorrências, aplicando filtros para data, aluno, matrícula"
    )
    public ResponseEntity buscarTodos(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dataOcorrencia") String sortBy,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) String nomeAluno
    ){

        Pageable pageable = PageRequest.of(page, size,Sort.by(sortBy));

       Page<OcorrenciaResponse> ocorrencias = ocorrenciaService.buscarTodasOcorrencias(dataInicial, dataFinal, matricula, nomeAluno, pageable);



        return ResponseEntity.ok(ocorrencias);

    }



    @GetMapping("/minhas")
    @Operation(
            summary = "Busca todas ocorrências registradas por um usuário logado",
            description = "Permite ao usuário do sistema ver todas as suas ocorrencias lançadas, pode filtrar por data"
    )
    public ResponseEntity buscarMinhasOcorrencias(HttpServletRequest httpServletRequest,
        @RequestParam(defaultValue = "0")  int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "dataOcorrencia") String sortBy,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<OcorrenciaResponse> ocorrencias = ocorrenciaService.buscarMinhasOcorrencias(dataInicial, dataFinal, httpServletRequest, pageable );

        return ResponseEntity.ok(ocorrencias);

    }



    @PatchMapping("/atualizar/parcial/{id}")
    @Operation(
            summary = "Atualiza campos parciais de uma ocorrencia",
            description = "Permite ao usuário do sistema atualizar a decrição da ocorrencia e gravidade"
    )
    public ResponseEntity atualizarParcial(@RequestParam Long id,  @RequestBody OcorrenciaRequestPatch request){

        ocorrenciaService.atualizaParcial(id, request);


        return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Atualizado");
    }





}