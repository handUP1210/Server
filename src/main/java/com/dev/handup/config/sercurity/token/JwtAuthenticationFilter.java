package com.dev.handup.config.sercurity.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    // Jwt Component
    private final JwtTokenProvider jwtTokenProvider;

    // Jwt Provider 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request Jwt Token 유효성 검증
    // jwtTokenProvider.validateToken filter를 filterChain 등록
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        // Request 헤더에서 토큰 추출
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 토큰 유효 검증
        if (token != null && jwtTokenProvider.isValidToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);

            // Security Context에 객체 저장
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
