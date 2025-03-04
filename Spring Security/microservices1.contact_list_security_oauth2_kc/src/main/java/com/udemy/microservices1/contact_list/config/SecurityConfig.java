package com.udemy.microservices1.contact_list.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    private JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter){
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(cus -> cus.disable())
                .authorizeHttpRequests(aut ->
                    aut.requestMatchers(HttpMethod.POST, "contactList").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.DELETE, "contactList/**").hasAnyRole("OPERATOR", "ADMIN")
                       .requestMatchers("contactList").authenticated()
                       .anyRequest().permitAll())
                .oauth2ResourceServer(oaut2RS ->
                    oaut2RS.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                .sessionManagement(sessionManagment ->
                    sessionManagment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }

}
