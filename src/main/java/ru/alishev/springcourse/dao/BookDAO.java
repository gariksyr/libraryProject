package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("select * from book", new BookMapper());
    }
    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO book(title, author, book_year) values (?,?,?)", book.getTitle(), book.getAuthor(), book.getBook_year());
    }
    public Object show(int id){
        return jdbcTemplate.query("select * from book where book_id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }
    public void update(int id, Book book){
        jdbcTemplate.update("UPDATE book SET title=?, book_year=?, author=? WHERE book_id=?", book.getTitle(), book.getBook_year(), book.getAuthor(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }
    public void setUser(int book_id, int person_id){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person_id, book_id);
    }
    public void deleteUser(int book_id){
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE book_id=?", book_id);
    }
    public List<Book> getUsersBooks(int id){
        return jdbcTemplate.query("select * from book where person_id=?", new Object[]{id}, new BookMapper());
    }
}
