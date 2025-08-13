package ru.alishev.springcourse.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Book {

    private int book_id;
    private int person_id;
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "author cannot be empty")
    private String author;
    @NotNull(message = "year cannot be empty")
    private int book_year;

    public Book() {
    }

    public Book(int book_id, int person_id, String title, String author, int book_year) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.title = title;
        this.author = author;
        this.book_year = book_year;
    }

    public Book(int book_id, String title, String author, int book_year) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.book_year = book_year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBook_year() {
        return book_year;
    }

    public void setBook_year(int book_year) {
        this.book_year = book_year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
