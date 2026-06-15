package com.obando.cv_spring_boot.cv.controller;

import com.obando.cv_spring_boot.cv.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CvController {

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("title", "Cv Spring Boot - Cv Controller");

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        model.addAttribute("person", person);

        return "index";
    }
}
