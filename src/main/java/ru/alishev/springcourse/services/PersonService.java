package ru.alishev.springcourse.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> index(){
        return personRepository.findAll();
    }
    @Transactional
    public void addPerson(Person person){
        personRepository.save(person);
    }
    public Person show(int id){
        return personRepository.findById(id).orElse(null);
    }
    @Transactional
    public void update(int id, Person updatedPerson){
        Person person = personRepository.findById(id).orElse(null);
        System.out.println(person.getBooks());
        person.setName(updatedPerson.getName());
        person.setBirth_year(updatedPerson.getBirth_year());
        personRepository.save(person);
    }
    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }
    public Optional<Person> findPersonByName(String name){
        return personRepository.findPersonByName(name);
    }
    public List<Book> getUsersBooks(int id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }
}
