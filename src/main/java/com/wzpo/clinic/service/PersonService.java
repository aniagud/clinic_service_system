package com.wzpo.clinic.service;

import com.wzpo.clinic.dto.PersonDTO;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public List<Person> getAllPersonByRole(String role){
        return personRepository.findAllByRole(role);
    }

    public List<Person> getPersonByName(String name){
        return personRepository.findAllByName(name);
    }

    public List<Person> getPersonBySurname(String surname){
        return personRepository.findAllBySurname(surname);
    }

    public Optional<Person> getPersonByPesel(String pesel){
        return personRepository.findOneByPesel(pesel);
    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean existsPersonByName(String name){
        return personRepository.findOneByName(name).isPresent();
    }

    public Boolean existsPersonByPesel(String pesel){
        return personRepository.findOneByPesel(pesel).isPresent();
    }

    public Boolean existsPersonById(Integer id) { return personRepository.findById(id).isPresent(); }
}
