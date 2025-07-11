package com.project.argoss.infrastructure.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.argoss.domain.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

//    @Value("${jwt.secret}")
    private String secret = "my-secret-security-key";
    private Algorithm algorithm = Algorithm.HMAC256(secret);



    public String generateToken(Usuario usuario){
        try{
            String token = JWT.create()
                    .withIssuer("argos-api")
                    .withSubject(usuario.getId())
                    .withClaim("email", usuario.getEmail())
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
