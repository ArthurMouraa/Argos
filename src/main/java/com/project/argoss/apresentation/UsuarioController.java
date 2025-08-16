package com.project.argoss.apresentation;

import com.project.argoss.aplication.dto.UsuarioResponse;
import com.project.argoss.aplication.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/me/{id}")
    public ResponseEntity credencial(String id){

        UsuarioResponse usuario = usuarioService.credencial(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }


}
