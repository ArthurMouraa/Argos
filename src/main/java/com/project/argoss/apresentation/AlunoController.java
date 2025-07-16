package com.project.argoss.apresentation;


import com.project.argoss.aplication.dto.AlunoRequest;
import com.project.argoss.aplication.dto.AlunoResponse;
import com.project.argoss.aplication.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/discente")
public class AlunoController {

    @Autowired
    private final AlunoService alunoService;

    @PostMapping("/criar")
    public ResponseEntity criar(@RequestBody AlunoRequest request){
        AlunoResponse aluno =  alunoService.criar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @PostMapping("/turma/{id}")
    public ResponseEntity listarPorTurma(@PathVariable Long id){
        List<AlunoResponse> alunos = alunoService.listarAlunosPorTurma(id);

        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @GetMapping("/nome")
    public ResponseEntity buscarPorNome(@RequestParam String nome){
        List<AlunoResponse> alunos =  alunoService.buscarPorNome(nome);

        return  ResponseEntity.status(HttpStatus.OK).body(alunos);
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AlunoRequest request){
        AlunoResponse alunoResponse =  alunoService.atualizar(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(alunoResponse);

    }

    @DeleteMapping("/aluno/{id}")
    public  ResponseEntity deletar(@PathVariable Long id){
        alunoService.deletar(id);

        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado");
    }




}