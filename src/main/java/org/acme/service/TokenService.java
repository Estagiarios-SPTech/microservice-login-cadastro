package org.acme.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.User;

@ApplicationScoped
public class TokenService {
    public String gerarToken(User user){
        return Jwt.issuer("http://localhost:8080/gerarToken")
                .upn(user.getEmail())
                .groups(user.getRole())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .sign();
    }
}
