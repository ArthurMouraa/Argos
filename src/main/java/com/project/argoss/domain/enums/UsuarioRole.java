package com.project.argoss.domain.enums;

public enum UsuarioRole {
    ADMIN("role_admin"),
    USER("role_user");

    String role;

    UsuarioRole(String role){
        this.role = role;
    }

}