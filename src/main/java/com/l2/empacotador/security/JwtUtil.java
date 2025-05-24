package com.l2.empacotador.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Chave secreta: tem que ser grande (no mínimo 256 bits = 32 chars)
    private static final String SECRET_KEY = "essa-chave-tem-que-ser-bem-grande-melhor-32-chars";

    // Chave segura para o JJWT usar
    private final Key key;

    public JwtUtil() {
        // Converte a string em uma chave segura para HS256
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Gera o token JWT com usuário, validade e assinatura
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                        // quem é o dono do token
                .setIssuer("empacotador-api")                // quem gerou o token
                .setIssuedAt(new Date())                      // data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora de validade
                .signWith(key, SignatureAlgorithm.HS256)     // assina com a chave segura
                .compact();
    }

    // Valida o token: verifica se está correto e não expirado
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Token inválido, expirado ou modificado
            return false;
        }
    }

    // Pega o username dentro do token para identificar o usuário
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
