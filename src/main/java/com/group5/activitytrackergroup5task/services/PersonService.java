package com.group5.activitytrackergroup5task.services;

import com.group5.activitytrackergroup5task.dtos.LoginDto;
import com.group5.activitytrackergroup5task.dtos.PersonDto;
import com.group5.activitytrackergroup5task.models.Person;

public interface PersonService {
    void createPerson(PersonDto personDto);
    Person login(LoginDto loginDto);
    Person getPerson(Long id);


}
