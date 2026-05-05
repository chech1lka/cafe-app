package com.cafe.app.service;

import com.cafe.app.model.Person;
import com.cafe.app.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
