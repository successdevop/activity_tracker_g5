package com.group5.activitytrackergroup5task.controllers;


import com.group5.activitytrackergroup5task.dtos.TodoDto;
import com.group5.activitytrackergroup5task.models.Person;
import com.group5.activitytrackergroup5task.models.Todo;
import com.group5.activitytrackergroup5task.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("/home")
    public String displayTracker(Model model,  HttpServletRequest request){
        Person person = (Person) request.getSession().getAttribute("user");
        TodoDto todoDto = new TodoDto();
        model.addAttribute("person", person);
        model.addAttribute("todoDto", todoDto);
        List<Todo> myTodos = todoService.getTodoList().stream()
                .filter(t -> t.getPerson().getId() == person.getId())
                .collect(Collectors.toList());

       Long in_progres = todoService.getTodoList().stream()
                .filter(t -> t.getStatus().equals("in-progress") &&
                        t.getPerson().getId() == person.getId()).count();

        Long completed = todoService.getTodoList().stream()
                .filter(t -> t.getStatus().equals("completed") &&
                        t.getPerson().getId() == person.getId()).count();

        model.addAttribute("myTodos", myTodos);
        model.addAttribute("completed", completed);
        model.addAttribute("in_progres", in_progres);
        return "tracker";
    }

    @PostMapping("/addTask")
    public String creatTodo(@ModelAttribute("todoDto") TodoDto todoDto,HttpServletRequest request) {
        todoService.saveTodo(todoDto,request);
        return "redirect:/home";
    }
    @GetMapping("/delete/{del}")  //
    public String deleteTodo(@PathVariable("del") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/home";
    }

    @GetMapping("/isCompleted/{id}")
    public String updateTodo(@PathVariable("id") Long id) {
        Todo exTodo = todoService.getTodo(id);
        exTodo.setStatus("completed");
        todoService.updateStatus(exTodo);
        return "redirect:/home";
    }

//    @GetMapping("/all")
//    public List<Todo> getTodoList() {
//        return todoService.getTodoList();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Todo> getTodo(@PathVariable("id") Long id) {
//        Todo todo = todoService.getTodo(id);
//        return todo != null ? new ResponseEntity<>(todo, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//

}
