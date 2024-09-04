package com.Coding.SecurityApplication.SecurityApplication.filters;

import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import com.Coding.SecurityApplication.SecurityApplication.service.JwtService;
import com.Coding.SecurityApplication.SecurityApplication.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtRequestFilter(JwtService jwtService,UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenValue = request.getHeader("Authorization");

        if(tokenValue == null || !tokenValue.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }

        String actualToken = tokenValue.split("Bearer ")[1];

        Long userId = jwtService.generateIdFromToken(actualToken);

        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User userResponse = userService.getUserById(userId);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userResponse,null,userResponse.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request,response);
    }
}
