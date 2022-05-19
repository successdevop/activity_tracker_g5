package com.group5.activitytrackergroup5task.services.impl;

import com.group5.activitytrackergroup5task.dtos.TodoDto;
import com.group5.activitytrackergroup5task.models.Person;
import com.group5.activitytrackergroup5task.models.Todo;
import com.group5.activitytrackergroup5task.repositories.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = {TodoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TodoServiceImplTest {
    @MockBean
    private TodoRepository todoRepository;
    @Autowired
    private TodoServiceImpl todoServiceImpl;
    @Test
    void shouldGetTodoListFromListOfTodos() {
        ArrayList<Todo> todoList = new ArrayList<>();
        when(this.todoRepository.findAll()).thenReturn(todoList);
        List<Todo> actualTodoList = this.todoServiceImpl.getTodoList();
        assertSame(todoList, actualTodoList);
        assertTrue(actualTodoList.isEmpty());
        verify(this.todoRepository).findAll();
    }
    @Test
    void shouldSaveANewTodo() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setId(123L);
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        person.setTodos(new ArrayList<>());
        Todo todo = new Todo();
        todo.setDescription("The characteristics of someone or something");
        todo.setEndOn("End On");
        todo.setId(123L);
        todo.setPerson(person);
        todo.setScheduledOn("Scheduled On");
        todo.setStatus("Status");
        todo.setTitle("Dr");
        when(this.todoRepository.save((Todo) org.mockito.Mockito.any())).thenReturn(todo);
        TodoDto todoDto = new TodoDto();
        assertSame(todo, this.todoServiceImpl.saveTodo(todoDto, new MockHttpServletRequest()));
        verify(this.todoRepository).save((Todo) org.mockito.Mockito.any());
    }
    @Test
    void shouldFindTodoById() {
        Todo todo = new Todo();
        todo.setDescription("The characteristics of someone or something");
        todo.setEndOn("17:12");
        todo.setId(123L);
        todo.setScheduledOn("21:59");
        todo.setTitle("Dr");
        Optional<Todo> ofResult = Optional.of(todo);
        when(this.todoRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(todo, this.todoServiceImpl.getTodo(123L));
        verify(this.todoRepository).findById((Long) any());
    }
    @Test
    void shouldDeleteTodoById() {
        Todo todo = new Todo();
        todo.setDescription("The characteristics of someone or something");
        todo.setEndOn("22:35");
        todo.setId(123L);
        todo.setScheduledOn("21:34");
        todo.setTitle("Dr");
        Optional<Todo> ofResult = Optional.of(todo);
        doNothing().when(this.todoRepository).delete((Todo) any());
        when(this.todoRepository.findById((Long) any())).thenReturn(ofResult);
        assertTrue(this.todoServiceImpl.deleteTodo(123L));
        verify(this.todoRepository).findById((Long) any());
        verify(this.todoRepository).delete((Todo) any());
    }

}