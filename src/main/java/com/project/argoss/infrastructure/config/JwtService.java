package com.project.argoss.infrastructure.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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

        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getUsername());;

        try{
            String token = JWT.create()
                    .withIssuer("argos-api")
                    .withSubject(user.get().getId())
                    .withClaim("email", user.get().getEmail())
                    .withExpiresAt(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                    .sign(algorithm);

            return token;

        }catch(JWTCreationException exception){
            return new JWTCreationException("erro ao gerar token", exception).toString();
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
            return new RuntimeException("token invalido", exception).toString();
        }
    }



    public String extractEmail(String token){
        try {

            String email = JWT.require(algorithm)
                    .withIssuer("argos-api")
                    .build()
                    .verify(token)
                    .getClaim("email")
                    .asString();

            return email;

        }catch (JWTVerificationException exception){
            return new RuntimeException("token invalido ou claim ausente", exception).toString();
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
            throw new RuntimeException("token invalido ou claim ausente", exception);
        }

    }


    public String idUsuarioLogado(HttpServletRequest httpServletRequest){
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("token invalido ou mal formatado");
        }

        String token = authHeader.replace("Bearer ", "");
        String idUserLogado = extractSubject(token);

        return  idUserLogado;
    }


}

