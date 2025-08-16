package com.project.argoss.apresentation;


import com.project.argoss.aplication.dto.AuthDto;
import com.project.argoss.aplication.dto.UsuarioRequest;
import com.project.argoss.aplication.dto.UsuarioResponse;
import com.project.argoss.aplication.service.AuthService;
import com.project.argoss.domain.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UsuarioMapper usuarioMapper;


    @PostMapping("/login")
    @Operation(
            summary = "Faz login para acesso",
            description = "O sistema é destinado para uso de professores, os quais são usuários, para ter acesso a todas as rotas faça login"
    )
    public ResponseEntity login(@RequestBody AuthDto dto){
        var respService =   authService.login(dto);
        System.out.println(">>> Login recebido: " + dto.email());
        System.out.println("SENHA recebida: " + dto.senha());


        return ResponseEntity.ok(respService);
    }


    @PostMapping("/register")
    @Operation(
            summary = "Cria uma conta",
            description = "O sistema é destinado para uso de professores, os quais são usuários, para ter acesso a todas as rotas, faça login"
    )
    public ResponseEntity<UsuarioResponse> register(@RequestBody UsuarioRequest dto){
        var user =  authService.register(dto);
        var response = usuarioMapper.toResponse(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    };



}
