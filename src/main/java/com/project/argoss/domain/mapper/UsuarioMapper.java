package com.project.argoss.domain.mapper;

import com.project.argoss.aplication.dto.UsuarioRequest;
import com.project.argoss.aplication.dto.UsuarioResponse;
import com.project.argoss.domain.entity.Usuario;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {


    public Usuario toEntity(UsuarioRequest usuarioDto){
        Usuario user = new Usuario();
        user.setEmail(usuarioDto.email());
        user.setSenha(usuarioDto.senha());
        user.setRole(usuarioDto.role());
        user.setMatricula(usuarioDto.matricula());
        user.setNome(usuarioDto.nome());
        user.setFoto(usuarioDto.foto());

        return user;
    }


    public UsuarioResponse toResponse(Usuario usuario){
        UsuarioResponse dtoResponse = new UsuarioResponse(usuario.getId(), usuario.getEmail(), usuario.getMatricula(), usuario.getNome(), usuario.getFoto());

        return dtoResponse;
    }

}