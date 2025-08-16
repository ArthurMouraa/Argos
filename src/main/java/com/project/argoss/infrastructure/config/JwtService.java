package com.project.argoss.infrastructure.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.argoss.apresentation.exception.InvalidateCredentialsException;
import com.project.argoss.apresentation.exception.RecursoNaoEncontradoException;
import com.project.argoss.domain.entity.Usuario;
import com.project.argoss.domain.repository.UsuarioRepository;
import com.project.argoss.infrastructure.details.UserDetailsImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

//@AllArgsConstructor
@Service
public class JwtService {



    private String secret = "my-secret-security-key";

    @Autowired
    private UsuarioRepository usuarioRepository;


    Algorithm algorithm = Algorithm.HMAC256(secret);

    public String generateToken(UserDetailsImp usuario){

        Usuario user = usuarioRepository.findByEmail(usuario.getUsername()).orElseThrow(() -> new RecursoNaoEncontradoException("jwtService: Usuario nao encontado"));

        try{
            String token = JWT.create()
                    .withIssuer("argos-api")
                    .withSubject(user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                    .sign(algorithm);

            return token;

        }catch(JWTCreationException exception){
            throw new JWTCreationException("erro ao gerar token", exception);
        }
    }


    public String validateToken(String token){
        try {
            String subject = JWT.require(algorithm)
                    .withIssuer("argos-api")
                    .build()
                    .verify(token)
                    .getSubject();

            return subject;

        } catch(JWTVerificationException exception){
            throw new JWTVerificationException("Token invalido", exception);
        }
    }

    public String extractSubject(String token) {
        try {

            String id = JWT.require(algorithm)
                    .withIssuer("argos-api")
                    .build()
                    .verify(token)
                    .getSubject();

            return id;

        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token invalido ou claim ausente", exception);
        }

    }


    public String idUsuarioLogado(HttpServletRequest httpServletRequest){
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new InvalidateCredentialsException("Token invalido ou mal formatado");
        }

        String token = authHeader.replace("Bearer ", "");
        String idUserLogado = extractSubject(token);

        return  idUserLogado;
    }


}

