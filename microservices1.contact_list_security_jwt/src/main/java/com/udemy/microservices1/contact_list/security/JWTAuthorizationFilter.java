package com.udemy.microservices1.contact_list.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.udemy.microservices1.contact_list.util.Constants.*;
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = servletRequest.getHeader(HEADER_NAME);
        //header not exists or not is the specificated one. For example: "Bearer ".
        if(token == null || !token.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(servletRequest, servletResponse); //spring security handle the validation
            return;
        }
        //Token exists so I get it
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
        //Set in the security context the authentication token
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(TOKEN_KEY)
                .build()
                .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //clean the prefix from the JWT and validate the token using the key
                .getBody(); //get the "body" (claims) from the token
        List<String> authorities = (List<String>) claims.get("authorities");
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities.stream() //convert the string authorities in SimpleGrantedAuthority instances
                                                                                                .map(SimpleGrantedAuthority::new)
                                                                                                .collect(Collectors.toList()));
    }
}
