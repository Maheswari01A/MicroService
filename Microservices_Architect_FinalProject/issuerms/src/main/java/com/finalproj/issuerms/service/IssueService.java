package com.finalproj.issuerms.service;

import com.finalproj.issuerms.model.Book;
import com.finalproj.issuerms.model.Issue;
import com.finalproj.issuerms.repository.IssueRepository;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    @Autowired
    private WebClient webClient;

    public IssueService(IssueRepository issueRepository, WebClient.Builder webClientBuilder) {
        this.issueRepository = issueRepository;
    }



    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElse(null);
    }

    public Issue issueBook(Issue issue) {
        // Check book availability and update issuedCopies in BookService
        Book book = getBook(issue);

        if (book != null && book.getIssuedCopies() + issue.getNoOfCopies() <= book.getTotalCopies()) {
            book.setIssuedCopies(book.getIssuedCopies() + issue.getNoOfCopies());

            updateBook(book);

            issue.setIssuedDate(LocalDateTime.now());
            return issueRepository.save(issue);
        }

        return null;
    }

    public void cancelIssue(Long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        if (issue != null) {
            Book book = getBook(issue);
            if (book != null) {
                book.setIssuedCopies(book.getIssuedCopies() - issue.getNoOfCopies());
                updateBook(book);
            }

            issueRepository.deleteById(id);
        }
    }
 
    @CircuitBreaker(name = "bookmsclient", fallbackMethod = "bookmsfallback")   
    private void updateBook(Book book) {
       webClient.post().
                uri("/api/books").
                body(Mono.just(book),Book.class).retrieve().bodyToMono(Book.class).block();
    }

    @CircuitBreaker(name = "bookmsclient", fallbackMethod = "bookmsfallback")  
    private Book getBook(Issue issue) {
        Book book = webClient.get().
                uri("/api/books/{isbn}", issue.getIsbn())
                .retrieve()
                .bodyToMono(Book.class).block();
        return book;
    }

    private Mono bookmsfallback(Exception exception) {
        System.out.println("***** Exception occured *****");
        exception.printStackTrace(); // just for demo purpose
        // add our logic of fallback
        return Mono.just("Exception occured"); // return just static string
    }

    private Mono bookmsfallback(CallNotPermittedException exception) {
        System.out.println("***** CallNotPermittedException occured *****");
        exception.printStackTrace(); // just for demo purpose
        // add our logic of fallback
        return Mono.just("CallNotPermittedException occured");
    }
}
