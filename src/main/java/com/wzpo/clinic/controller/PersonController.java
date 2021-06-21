package com.wzpo.clinic.controller;

import com.wzpo.clinic.dto.PersonDTO;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping(value = "api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Person> getAllCategories(){
        return personService.getAllPerson();
    }
}
