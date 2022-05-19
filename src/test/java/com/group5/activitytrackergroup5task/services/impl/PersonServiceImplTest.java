package com.group5.activitytrackergroup5task.services.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group5.activitytrackergroup5task.dtos.LoginDto;
import com.group5.activitytrackergroup5task.dtos.PersonDto;
import com.group5.activitytrackergroup5task.models.Person;
import com.group5.activitytrackergroup5task.repositories.PersonRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PersonServiceImplTest {
    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonServiceImpl personServiceImpl;


    @Test
    void testToCreateNewPerson() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setId(123L);
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.personRepository.save((Person) any())).thenReturn(person);
        this.personServiceImpl
                .createPerson(new PersonDto("Fname", "Lname", "jane.doe@example.org", "iloveyou", "iloveyou"));
        verify(this.personRepository).save((Person) any());
    }




    @Test
    void shouldLoginUserIn() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setId(123L);
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Person> ofResult = Optional.of(person);
        when(this.personRepository.findByEmail((String) any())).thenReturn(ofResult);
        assertSame(person, this.personServiceImpl.login(new LoginDto("jane.doe@example.org", "iloveyou")));
        verify(this.personRepository).findByEmail((String) any());
    }


}

