package com.project.argoss.aplication.service;


import com.project.argoss.aplication.dto.AuthDto;
import com.project.argoss.aplication.dto.UsuarioRequest;
import com.project.argoss.domain.entity.Usuario;
import com.project.argoss.domain.mapper.UsuarioMapper;
import com.project.argoss.domain.repository.UsuarioRepository;
import com.project.argoss.infrastructure.config.JwtService;
import com.project.argoss.infrastructure.details.UserDetailsImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UsuarioMapper usuarioMapper;





    public  String login(AuthDto dto){
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());

            var auth = authenticationManager.authenticate(usernamePassword);

            UserDetailsImp userDetails = (UserDetailsImp)  auth.getPrincipal();



            var token = jwtService.generateToken(userDetails);

            return token;

        }catch (BadCredentialsException exception){
            throw  new RuntimeException(exception);

        }

    };


    public Usuario register(UsuarioRequest dto){
        Usuario user = usuarioMapper.toEntity(dto);
        String encryptPassword = new BCryptPasswordEncoder().encode(dto.senha());
        user.setSenha(encryptPassword);
        usuarioRepository.save(user);

        return user;
    }



}