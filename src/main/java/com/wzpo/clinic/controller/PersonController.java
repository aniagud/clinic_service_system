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

    //Getting a list

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    //Searching for person

    @GetMapping("/name/{name}")
    public List<Person> showPersonByName(@PathVariable("name") String name){

        return (personService.getPersonByName(name));
    }

    @GetMapping("/surname/{surname}")
    public List<Person> showPersonBySurname(@PathVariable("surname") String surname){

        return(personService.getPersonBySurname(surname));
    }

    @GetMapping("/pesel/{pesel}")
    public Person showPersonByPesel(@PathVariable("pesel") String pesel){

        Person person = personService.getPersonByPesel(pesel)
                .orElseThrow( () -> new IllegalArgumentException("Invalid category name: " + pesel));

        return person;
    }


    //Adding a new person

    @PostMapping(value = "/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody final PersonDTO personDTO){

        try{
            personService.addPerson(personDTO);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to add a person to database for person {0}", personDTO.getSurname()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    //Updating a person data

    @PutMapping("/update/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person person){

        try{
            personService.updatePerson(person);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to update in database a person with id {0} and name {1}",person.getId(),person.getName()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Deleting a person

    @DeleteMapping("/delete/{pesel}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Person> deletePerson(@PathVariable("pesel") String pesel){

        Person person = personService.getPersonByPesel(pesel)
                .orElseThrow( () -> new IllegalArgumentException("Invalid personal pesel: " + pesel));

        try{
            personService.deletePerson(person);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to delete from a database a person with id {0} and pesel {1}", person.getId(), person.getPesel()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
