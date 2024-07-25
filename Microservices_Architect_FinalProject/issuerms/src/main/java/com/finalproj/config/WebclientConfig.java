package com.finalproj.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// MRI  : Writing code for calling userms from order ms.
//For any  custom configuration, we need @Been to define it
@Configuration
public class WebclientConfig {

    @Bean //MRI - Bean Definition for WebClient.Builder
    @LoadBalanced // Load balance on web client
    public WebClient.Builder builder() {
        //client-side load balancing
        //WebClient comes from webfulx its going to make use of the the userms. 
        //Its the recommended client from spring team
        //giving the name of the service. This name will be translated to ip using eureka
        return WebClient.builder().baseUrl("http://bookms");
    }

    @Bean //MRI - Bean Definition for WebClient
    //@Bean: Marks this method as a bean producer method, 
    //meaning the return value of this method will be registered as a 
    //bean in the Spring application context.
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}
