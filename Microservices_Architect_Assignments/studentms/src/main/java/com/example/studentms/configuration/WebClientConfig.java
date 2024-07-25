package com.example.studentms.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean 
    @LoadBalanced // Load balance on web client
    public WebClient.Builder builder() {    
        return WebClient.builder().baseUrl("http://feesms"); 
    }

    @Bean 
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}
