package com.project.argoss.aplication.service;

import com.project.argoss.aplication.dto.UsuarioResponse;
import com.project.argoss.apresentation.exception.RecursoNaoEncontradoException;
import com.project.argoss.domain.entity.Usuario;
import com.project.argoss.domain.mapper.UsuarioMapper;
import com.project.argoss.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioResponse credencial(String id){
        Usuario usuario= usuarioRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("usuario nao encontrado")
        );

       UsuarioResponse resp = usuarioMapper.toResponse(usuario);

       return  resp;
    }
}
