package com.obando.cv_spring_boot.cv.rest;

import com.obando.cv_spring_boot.cv.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CvApiController {

    @GetMapping("/cv")
    public Person getPerson() {
        return new Person("Jose","Obando");
    }
}
