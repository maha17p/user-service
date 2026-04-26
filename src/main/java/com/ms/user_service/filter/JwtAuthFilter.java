package com.ms.user_service.filter;

import com.ms.user_service.common.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader(SecurityConstants.AUTH_HEADER);
        if(authToken != null && authToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            String token = authToken.substring(7);
        }
        filterChain.doFilter(request,response);
    }
}
