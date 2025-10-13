package ru.alishev.springcourse.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Entity
@Table(name = "person")
public class Person {
    @Column(name = "person_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;
    @Column(name = "name")
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @Min(value = 1900, message = "too low value you're lying")
    @Max(value = 2025, message = "now is only 2025")
    @NotNull(message = "year cannot be empty")
    @Column(name = "birth_year")
    private int birth_year;
    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public Person(int id, String name, int birth_year) {
        this.person_id = id;
        this.name = name;
        this.birth_year = birth_year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
