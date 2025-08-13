package ru.alishev.springcourse.dao;

import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import ru.alishev.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper {
    @Override
    public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setName(rs.getString("name"));
        person.setPerson_id(rs.getInt("person_id"));
        person.setBirth_year(rs.getInt("birth_year"));
        return person;
    }
}
