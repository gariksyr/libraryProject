package ru.alishev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Column(name = "book_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    @NotEmpty(message = "title cannot be empty")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "author cannot be empty")
    @Column(name = "author")
    private String author;
    @NotNull(message = "year cannot be empty")
    @Column(name = "book_year")
    private int bookYear;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateOfPossession")
    private LocalDateTime dateOfPossession;
    public Book() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Book(int book_id, String title, String author, int book_year, Person person) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.bookYear = book_year;
        this.person = person;
    }

    public Book(int book_id,String title, String author, int book_year) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.bookYear = book_year;
    }


    public LocalDateTime getDateOfPossession() {
        return dateOfPossession;
    }

    public void setDateOfPossession(LocalDateTime dateOfPossession) {
        this.dateOfPossession = dateOfPossession;
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
        return bookYear;
    }

    public void setBook_year(int book_year) {
        this.bookYear = book_year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public boolean isOverdue(){
        return LocalDateTime.now().minusDays(10).isAfter(this.getDateOfPossession());
    }
}
