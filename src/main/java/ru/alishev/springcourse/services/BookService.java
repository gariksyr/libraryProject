package ru.alishev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
        return bookRepository.findAll();
    }
    public List<Book> indexSortedAndPaginated(int page, int books_per_page){
        return bookRepository.findAll(PageRequest.of(page, books_per_page, Sort.by("bookYear"))).getContent();
    }
    public List<Book> indexPaginated(int page, int books_per_page){
        return bookRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
    }
    public List<Book> indexSorted(){
        return bookRepository.findAll(Sort.by("bookYear"));
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
        book.setDateOfPossession(updatedBook.getDateOfPossession());
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
        book.setDateOfPossession(LocalDateTime.now());
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
        book.setDateOfPossession(null);
    }
}
