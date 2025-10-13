package ru.alishev.springcourse.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PersonService personService;


    @Autowired
    public BookService(BookRepository bookRepository, PersonService personService) {
        this.bookRepository = bookRepository;
        this.personService = personService;
    }
    public List<Book> index(){
        return  bookRepository.findAll();
    }
    @Transactional
    public void addBook(Book book){
        bookRepository.save(book);
    }
    public Book show(int id){
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    public void update(int id, Book updatedBook){
        Book book = bookRepository.findById(id).orElse(null);
        book.setBook_year(updatedBook.getBook_year());
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        bookRepository.save(book);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
    @Transactional
    public void setUser(int book_id, int person_id){
        Book book = bookRepository.findById(book_id).orElse(null);
        Person person = personService.show(person_id);
        book.setPerson(person);
        if (person.getBooks() == null){
            person.setBooks(Collections.singletonList(book));
        }
        else {
            person.getBooks().add(book);
        }
        bookRepository.save(book);
    }
    @Transactional
    public void deleteUser(int id){
        Book book = bookRepository.findById(id).orElse(null);
        book.setPerson(null);
    }
}
