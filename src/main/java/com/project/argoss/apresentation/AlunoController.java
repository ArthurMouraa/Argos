package com.project.argoss.apresentation;


import com.project.argoss.aplication.dto.AlunoRequest;
import com.project.argoss.aplication.dto.AlunoRequestPatch;
import com.project.argoss.aplication.dto.AlunoResponse;
import com.project.argoss.aplication.service.AlunoService;
import com.project.argoss.aplication.util.AlunoSpecification;
import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/discente")
public class AlunoController {

    @Autowired
    private final AlunoService alunoService;

    @PostMapping("/criar")
    @Operation(
            summary = "Cadastra um aluno",
            description = "Registra um novo aluno."
    )
    public ResponseEntity criar(@RequestBody AlunoRequest request){
        AlunoResponse aluno =  alunoService.criar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @PostMapping("/turma/{id}")
    @Operation(
            summary = "Trás todos alunos de uma turma",
            description = "Busca todos os alunos de uma turma, recbe id da turma como parâmetro"
    )
    public ResponseEntity listarPorTurma(
            @PathVariable Long id
            ){
        List<AlunoResponse> alunos = alunoService.listarAlunosPorTurma(id);

        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @GetMapping("/nome")
    @Operation(
            summary = "Pesquisa um aluno por nome",
            description = "Pesquisa um registro de por nome, caso esse nome não exista, é retornado um array vazio."
    )
    public ResponseEntity buscarPorNome(@RequestParam String nome){
        List<AlunoResponse> alunos =  alunoService.buscarPorNome(nome);

        return  ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @GetMapping("/todos")
    @Operation(
            summary = "Trás todos os alunos",
            description = "Busca todos os registros de alunos. Filtros: Alunos ativos ou inativos (Status: ON OF) - Nome -Turma - Data "
    )
    public ResponseEntity<Page<AlunoResponse>> buscarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(required = false)Status status,
            @RequestParam(required = false)String nome,
            @RequestParam(required = false) Long turmaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
            ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<Specification<Aluno>> specs = List.of(
                AlunoSpecification.comNome(nome),
                AlunoSpecification.comStatus(status),
                AlunoSpecification.comTurma(turmaId),
                AlunoSpecification.comDataEntre(dataInicial, dataFinal)
        );


        Specification<Aluno> finalSpec = Specification.allOf(specs);

        Page<AlunoResponse> alunosPage = alunoService.buscarAlunos(finalSpec, pageable);
        return ResponseEntity.ok(alunosPage);

    }


    @PatchMapping("/atualizar/{id}")
    @Operation(
            summary = "Atualiza de forma parcial",
            description = "Atualiza apenas campos específicos de alunos"
    )
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AlunoRequestPatch request){
        AlunoResponse alunoResponse =  alunoService.atualizar(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(alunoResponse);

    }

    @DeleteMapping("/aluno/{id}")
    @Operation(
            summary = "Deleta um aluno",
            description = "Deleta um registro de um  aluno"
    )
    public  ResponseEntity deletar(@PathVariable Long id){
        alunoService.deletar(id);

        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado");
    }




}