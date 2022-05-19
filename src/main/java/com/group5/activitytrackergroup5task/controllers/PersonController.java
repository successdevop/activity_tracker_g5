package com.group5.activitytrackergroup5task.controllers;
import com.group5.activitytrackergroup5task.dtos.LoginDto;
import com.group5.activitytrackergroup5task.dtos.PersonDto;
import com.group5.activitytrackergroup5task.dtos.TodoDto;
import com.group5.activitytrackergroup5task.models.Person;
import com.group5.activitytrackergroup5task.services.PersonService;
import com.group5.activitytrackergroup5task.models.Todo;
import com.group5.activitytrackergroup5task.services.PersonService;
import com.group5.activitytrackergroup5task.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class PersonController {
    private final TodoService todoService;
    private final PersonService personService;

    @Autowired
    public PersonController(TodoService todoService, PersonService personService) {
        this.todoService = todoService;
        this.personService = personService;
    }

    @GetMapping("/signup")
    public String getSignup(Model model){
        PersonDto personDto = new PersonDto();
        model.addAttribute("personDto", personDto);
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute PersonDto personDto){
        personService.createPerson(personDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String login (Model model){
        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto", loginDto);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, HttpServletRequest request){
       HttpSession session =  request.getSession();
        Person person = personService.login(loginDto);
        if (person != null){
            session.setAttribute("user", person);
            return "redirect:/home";
        }
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){

        request.getSession().invalidate();
        return  "redirect:/";
    }
}
