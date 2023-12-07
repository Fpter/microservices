package com.example.Securityservice.filter;

import com.example.Securityservice.util.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

//    private final AuthenticationManager authenticationManager;


//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super();
//        this.authenticationManager = authenticationManager;
//        setFilterProcessesUrl("/api/public/login");
//    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String token = extractJwtFromRequest(request);

        if (token != null && JwtUtil.validateToken(token)) {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        }else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Authentication getAuthentication(String token) {
        String username = JwtUtil.getUsernameFromToken(token);
        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
