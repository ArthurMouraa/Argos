package com.project.argoss.infrastructure.details;


import com.project.argoss.domain.entity.Usuario;
import com.project.argoss.domain.enums.UsuarioRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImp implements UserDetails {

    @Autowired
    private final Usuario usuario;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(usuario.getRole() == UsuarioRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("role_user"), new SimpleGrantedAuthority("role_admin"));
        }
        return List.of(new SimpleGrantedAuthority("role_user"));
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
