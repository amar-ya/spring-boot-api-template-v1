package com.example.NewProject.security;

import com.example.NewProject.users.CustomUserDetails;
import com.example.NewProject.users.UserEntity;
import com.example.NewProject.users.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException
    {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String userEmail;



        //Check if header exists and starts with bearer
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        try
        {
            userEmail = jwtUtil.getEmailFromToken(token);
        } catch (ExpiredJwtException e)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //Only set authentication if not already done
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserEntity user = userRepository.findByEmail(userEmail).orElse(null);
            if (user != null) {
                CustomUserDetails userDetails = new CustomUserDetails(user);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
