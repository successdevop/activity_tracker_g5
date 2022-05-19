package com.group5.activitytrackergroup5task.services.impl;


import com.group5.activitytrackergroup5task.dtos.TodoDto;
import com.group5.activitytrackergroup5task.models.Person;
import com.group5.activitytrackergroup5task.models.Todo;
import com.group5.activitytrackergroup5task.repositories.TodoRepository;
import com.group5.activitytrackergroup5task.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {


    private final TodoRepository todoRepository;
    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    @Override
    public Todo saveTodo(TodoDto todoDto, HttpServletRequest request) {
        Todo todo = new Todo();
        Person person = (Person) request.getSession().getAttribute("user");
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setScheduledOn(todoDto.getScheduledOn());
        todo.setEndOn(todoDto.getEndOn());
        todo.setPerson(person);
        todo.setStatus("in-progress");
        return todoRepository.save(todo);
    }

    @Override
    public void updateStatus(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public boolean deleteTodo(Long id) {
        boolean deleted = false;
        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
            deleted = true;
        }
        return deleted;
    }
}