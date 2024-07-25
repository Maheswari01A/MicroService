package com.example.studentms.controller;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@RestController // MRI write complete class
public class FeesmsClientResource {

    @Autowired
    private WebClient webClient;

    @GetMapping("/client-fees/student/{studentId}")
    @CircuitBreaker(name = "feesmsclient", fallbackMethod = "feesmsfallback")     
    public Mono<Object> getFeesFromFeesms(@PathVariable("studentId") Integer studentId) {
        System.out.println("About to call feesms from studentms");
        //Partial uri given in WebClientConfig.java and hence give only the method name here.
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/fees/student/{studentId}")
                                                      .build(studentId))
                        .retrieve()
                        .bodyToMono(Object.class);
    }
    
    /**
     * This method is called when circuit breaker is in closed state but call failed
     * @param exception
     * @return
     */
    private Mono feesmsfallback(Exception exception) {
        System.out.println("***** Exception occured *****");
        exception.printStackTrace(); 
        return Mono.just("Exception occured"); // return just static string
    }

    /**
     * This method is called when circuit breaker is in open state
     * @param exception
     * @return
     */
    private Mono feesmsfallback(CallNotPermittedException exception) {
        System.out.println("***** CallNotPermittedException occured *****");
        exception.printStackTrace(); 
        return Mono.just("CallNotPermittedException occured");
    }
}
