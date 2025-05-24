package com.l2.empacotador.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();

        logger.info("[JwtFilter] URI interceptada: {}", uri);

        // Liberar /auth, /swagger-ui, /v3/api-docs e /error sem exigir token
        if (uri.startsWith("/auth") || uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs") || uri.startsWith("/error")) {
            logger.info("[JwtFilter] Rota pública, liberando acesso sem token: {}", uri);
            chain.doFilter(request, response);
            return;
        }

        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("[JwtFilter] Falha: Token ausente ou inválido para URI: {}", uri);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token ausente ou inválido");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            logger.warn("[JwtFilter] Falha: Token inválido ou expirado para URI: {}", uri);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
            return;
        }

        logger.info("[JwtFilter] Token válido para URI: {}", uri);
        chain.doFilter(request, response);
    }
}
