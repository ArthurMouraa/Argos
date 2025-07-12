package com.project.argoss.apresentation;


import com.project.argoss.aplication.dto.TurmaRequest;
import com.project.argoss.aplication.dto.TurmaResponse;
import com.project.argoss.aplication.service.TurmaService;
import com.project.argoss.domain.entity.Turma;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @PostMapping("/criar")
    public ResponseEntity criar(@RequestBody TurmaRequest request){
        Turma turma = turmaService.criar(request);

        return  ResponseEntity.status(HttpStatus.CREATED).body(turma);
    }

    @GetMapping("/todos")
    public ResponseEntity buscarTodos(){
        List<TurmaResponse> turmas = turmaService.buscarTodos();

        return ResponseEntity.status(HttpStatus.OK).body(turmas);
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity buscarId(@PathVariable Long id){

        TurmaResponse turma = turmaService.buscarPorId(id);

        return  ResponseEntity.status(HttpStatus.OK).body(turma);
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody TurmaRequest request){
        TurmaResponse turma =  turmaService.atualizar(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(turma);
    }


    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        turmaService.deletar(id);

        return   ResponseEntity.status(HttpStatus.OK).body("Turma deletada com sucesso");
    }



}
