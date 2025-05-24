package com.l2.empacotador.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.l2.empacotador.model.AuthRequest;
import com.l2.empacotador.model.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        System.out.println("Recebido login: " + request.getUsername() + " / " + request.getPassword());

        if ("admin".equals(request.getUsername()) && "senha123".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            System.out.println("Token gerado: " + token);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            System.out.println("Credenciais inválidas para usuário: " + request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
