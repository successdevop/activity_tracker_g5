package com.group5.activitytrackergroup5task.repositories;


import com.group5.activitytrackergroup5task.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
