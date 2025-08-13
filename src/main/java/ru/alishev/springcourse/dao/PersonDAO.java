package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }
    public void addPerson(Person person){
        jdbcTemplate.update("INSERT INTO person(name, birth_year) values (?,?)", person.getName(), person.getBirth_year());
    }
    public Person show(int id){
        return jdbcTemplate.query("select * from person where person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person SET name=?, birth_year=? WHERE person_id=?", person.getName(), person.getBirth_year(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }
    public Optional<Person> show(String name){
        return jdbcTemplate.query("select * from person where name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
