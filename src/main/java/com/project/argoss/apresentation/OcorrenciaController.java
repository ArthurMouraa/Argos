package com.project.argoss.apresentation;


import com.project.argoss.aplication.dto.OcorrenciaRequest;
import com.project.argoss.aplication.dto.OcorrenciaResponse;
import com.project.argoss.aplication.service.OcorrenciaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private final OcorrenciaService ocorrenciaService;

    @PostMapping("/criar")
    public ResponseEntity criar(@RequestBody OcorrenciaRequest request, HttpServletRequest httpServletRequest){

        OcorrenciaResponse ocorrencia = ocorrenciaService.criar(request, httpServletRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrencia);
    }


    @GetMapping("discente/{id}")
    public ResponseEntity buscarPorAluno(@PathVariable Long id){
        List<OcorrenciaResponse> ocorrencias = ocorrenciaService.ocorrenciaAluno(id);

        return ResponseEntity.status(HttpStatus.OK).body(ocorrencias);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody OcorrenciaRequest request){

        OcorrenciaResponse response =  ocorrenciaService.atualizar(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        ocorrenciaService.deletar(id);

        return ResponseEntity.status(HttpStatus.OK).body("ocorrencia deletada");
    }
}