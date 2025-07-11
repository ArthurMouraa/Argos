package com.project.argoss.aplication.dto;

import com.project.argoss.domain.enums.UsuarioRole;

public record UsuarioRequest(String email, String senha, UsuarioRole role, String matricula, String nome, String foto) {
}