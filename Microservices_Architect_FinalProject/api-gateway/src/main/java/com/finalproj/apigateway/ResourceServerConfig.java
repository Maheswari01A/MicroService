package com.finalproj.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class ResourceServerConfig {

    @Bean
    @Order(1)
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeExchange().anyExchange().authenticated(); // every request should be authenticate
        httpSecurity.oauth2ResourceServer(c -> c.jwt(j -> j.jwkSetUri("http://localhost:9000/oauth2/jwks")));
        return httpSecurity.build();
    }
}
