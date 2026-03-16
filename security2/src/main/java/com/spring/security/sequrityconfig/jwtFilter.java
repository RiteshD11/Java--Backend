package com.spring.security.sequrityconfig;

import com.spring.security.service.MyUserDetailService;
import com.spring.security.service.jwtService;
import io.jsonwebtoken.Header;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {
    @Autowired
    jwtService jwtservice;

    @Autowired
    ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // ****  here the space at Bearer is important as token start with this string

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtservice.extractUserName(token);
        }


        // validating the token

        // here we are checking wheather already the authentication object is there or not

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails=context.getBean(MyUserDetailService.class).loadUserByUsername(username);
            if (jwtservice.validate(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                // now we are assigning the token to SecurityContextHolder

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // so to pass to another filter in filter chain
        filterChain.doFilter(request,response);
    }


}
