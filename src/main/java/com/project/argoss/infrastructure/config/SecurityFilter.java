package com.project.argoss.infrastructure.config;

import com.project.argoss.domain.entity.Usuario;
import com.project.argoss.domain.repository.UsuarioRepository;
import com.project.argoss.infrastructure.details.UserDetailsImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UsuarioRepository usuarioRepository;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        var token = recoverToken(request);

        if(token != null){
            try{
                System.out.println("Token diferente de null chamado");
                var userId = jwtService.validateToken(token);
                Usuario user = usuarioRepository.findById(userId).orElseThrow(
                        () -> new RuntimeException("Usuario não encontrado")
                );

                UserDetailsImp detailsUser = new UserDetailsImp(user);

                var authentication = new UsernamePasswordAuthenticationToken(detailsUser,null, detailsUser.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (AuthenticationCredentialsNotFoundException exception){
                throw new ServletException("Autenticação falhou", exception);
            }

        }

         filterChain.doFilter(request, response);
    }



    public String recoverToken(HttpServletRequest httpServletRequest)
    {
        System.out.println("Recovertoken chamado");
        var authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            System.out.println("condicao do recover");
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }



}