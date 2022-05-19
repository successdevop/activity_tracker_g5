package com.group5.activitytrackergroup5task.repositories;

import com.group5.activitytrackergroup5task.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}
