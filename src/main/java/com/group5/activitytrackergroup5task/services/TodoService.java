package com.group5.activitytrackergroup5task.services;


import com.group5.activitytrackergroup5task.dtos.TodoDto;
import com.group5.activitytrackergroup5task.models.Todo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TodoService {

    List<Todo> getTodoList();
    Todo getTodo(Long id);
    Todo saveTodo(TodoDto todoDto, HttpServletRequest request);
    void updateStatus(Todo todo);
    boolean deleteTodo(Long id);
}