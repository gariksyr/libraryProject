package ru.alishev.springcourse.dao;

import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import ru.alishev.springcourse.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper {
    @Override
    public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBook_id(rs.getInt("book_id"));
        book.setPerson_id(rs.getInt("person_id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setBook_year(rs.getInt("book_year"));
        return book;
    }
}
