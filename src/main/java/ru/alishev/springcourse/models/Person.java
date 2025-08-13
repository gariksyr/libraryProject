package ru.alishev.springcourse.models;


import jakarta.validation.constraints.*;

import java.util.Date;

/**
 * @author Neil Alishev
 */
public class Person {
    private int person_id;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @Min(value = 1900, message = "too low value you're lying")
    @Max(value = 2025, message = "now is only 2025")
    @NotNull(message = "year cannot be empty")
    private int birth_year;

    public Person() {
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
