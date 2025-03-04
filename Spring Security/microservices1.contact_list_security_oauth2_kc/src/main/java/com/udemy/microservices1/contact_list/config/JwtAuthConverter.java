package com.udemy.microservices1.contact_list.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken>{
    @Value("${keycloak.clientId}")
    private String clientId;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
       Collection<GrantedAuthority> authorities = extractResourceRoles(jwt);
        //JwtAuthenticationToken is an implementation of the abstract class AbstractAuthenticationToken
       return new JwtAuthenticationToken(jwt, authorities, jwt.getClaim("preferred_username")); //preferred_username is a property of jwt which contains the username
    }

    private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt){ //get a collection of authorities
        try{
            Map<String, Object> resourceAccessMap = jwt.getClaimAsMap("resource_access");
            Map<String, Object> clientMap = (Map<String, Object>) resourceAccessMap.get(clientId);
            List<String> authorities = (List<String>) clientMap.get("roles");

            return authorities.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                    .collect(Collectors.toSet());

        }catch (NullPointerException | ClassCastException ex){
            log.info("Error extracting roles: "+ex.getMessage());
            return Set.of();
        }
    }
}
