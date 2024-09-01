package br.com.fiap.traffic_management_spring_boot.config.security;

import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTTokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String sign(Account account) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .create()
                    .withIssuer("issuer")
                    .withSubject(account.getEmail())
                    .withExpiresAt(generateExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw  new RuntimeException("Error on token creation");
        }
    }

    public String valid(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("issuer")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid Token");
        }
    }

    public Instant generateExpiresAt() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
