package com.obando.porfolio_backend.rest;

import com.obando.porfolio_backend.model.PersonalInfo;
import com.obando.porfolio_backend.service.IPersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/test-personal-info")
public class PersonalInfoTestController {

    private final IPersonalInfoService personalInfoService;

    public PersonalInfoTestController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/all")
    public List<PersonalInfo> getPersonalInfo(){
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id){
        Optional<PersonalInfo> optionalPersonalInfo = personalInfoService.findById(id);

        if(optionalPersonalInfo.isPresent()){
            return optionalPersonalInfo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal info not found");
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo){
        return ResponseEntity.ok(personalInfoService.save(personalInfo));
    }
}
