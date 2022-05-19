package com.group5.activitytrackergroup5task.services.impl;

import com.group5.activitytrackergroup5task.dtos.LoginDto;
import com.group5.activitytrackergroup5task.dtos.PersonDto;
import com.group5.activitytrackergroup5task.models.Person;
import com.group5.activitytrackergroup5task.repositories.PersonRepository;
import com.group5.activitytrackergroup5task.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getFname().concat(" "+ personDto.getLname()));
        person.setEmail(personDto.getEmail());
        person.setPassword(personDto.getPassword());

        personRepository.save(person);
    }

    @Override
    public Person login(LoginDto loginDto) {
        Optional<Person> person = personRepository.findByEmail(loginDto.getEmail());
        if (person.isPresent()){
            Person currentPerson = person.get();
            if (currentPerson.getPassword().equals(loginDto.getPassword())){
                return currentPerson;
            }
        }
        return null;
    }

    @Override
    public Person getPerson(Long id) {
        return null;
    }
}
