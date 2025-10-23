package ru.alishev.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.services.BookService;
import ru.alishev.springcourse.services.PersonService;


@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BooksController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(name = "sort_by_year", defaultValue = "false") boolean sort_by_year,
                        @RequestParam(name = "page", required = false) Integer page,
                        @RequestParam(name = "books_per_page",required = false) Integer books_per_page){
        if (sort_by_year && page != null){
            model.addAttribute("books", bookService.indexSortedAndPaginated(page, books_per_page));
        }
        else if (sort_by_year){
            model.addAttribute("books", bookService.indexSorted());
        }
        else if (page != null) {
            model.addAttribute("books", bookService.indexPaginated(page, books_per_page));
        }
        else {
            model.addAttribute("books", bookService.index());
        }
        return "books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/create";
    }
    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/create";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book",bookService.show(id));
        model.addAttribute("people", personService.index());
        model.addAttribute("personUser", bookService.show(id).getPerson());
        return "books/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookService.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/setPerson")
    public String setPerson(@PathVariable("id") int book_id, @ModelAttribute("person_id") int person_id){
        bookService.setUser(book_id, person_id);
        return "redirect:/books/" + book_id;
    }
    @PatchMapping("/{id}/deletePerson")
    public String deletePerson(@PathVariable("id") int book_id){
        bookService.deleteUser(book_id);
        return "redirect:/books/" + book_id;
    }
    @GetMapping("/search")
    public String searchBook(){
        return "books/search";
    }
    @GetMapping("/found")
    public String foundBooks(@RequestParam(name = "title") String title, Model model){
        model.addAttribute("books", bookService.foundBooks(title));
        return "books/found";
    }
}
