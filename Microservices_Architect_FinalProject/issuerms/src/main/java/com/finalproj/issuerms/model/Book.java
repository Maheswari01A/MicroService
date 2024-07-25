package com.finalproj.issuerms.model;

public class Book {

    private Long id;
    private String isbn;
    private String title;
    private String publishedDate;
    private int totalCopies;
    private int issuedCopies;
    private Author author;

    public Book() {
    }

    public Book(Long id, String isbn, String title, String publishedDate, int totalCopies, int issuedCopies, Author author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.publishedDate = publishedDate;
        this.totalCopies = totalCopies;
        this.issuedCopies = issuedCopies;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getIssuedCopies() {
        return issuedCopies;
    }

    public void setIssuedCopies(int issuedCopies) {
        this.issuedCopies = issuedCopies;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}