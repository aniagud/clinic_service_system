package com.wzpo.clinic.service;

import com.wzpo.clinic.dto.PatientDTO;
import com.wzpo.clinic.dto.PersonDTO;
import com.wzpo.clinic.entity.Patient;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.repository.PatientRepository;
import com.wzpo.clinic.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PersonRepository personRepository;

    public PatientService(PatientRepository patientRepository,
                          PersonRepository personRepository){
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
    }


    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id){
        return patientRepository.findOneById(id);
    }

    public Optional<Patient> getPatientByPerson(Person person){
        return patientRepository.findOneByPerson(person);
    }

    public List<Patient> getPatientByName(String name){
        return patientRepository.findAllByPerson_Name(name);
    }

    public List<Patient> getPatientBySurname(String surname){
        return patientRepository.findAllByPerson_Surname(surname);
    }

    public Optional<Patient> getPatientByPesel(String pesel){
        return patientRepository.findOneByPerson_Pesel(pesel);
    }

    public List<Patient> getPatientByGender(String gender){
        return patientRepository.findAllByPerson_Gender(gender);
    }


    public Boolean existsPatientByPesel(String pesel){
        return patientRepository.findOneByPerson_Pesel(pesel).isPresent();
    }

    public Boolean existsPatientById(Long id) { return patientRepository.findById(id).isPresent(); }


    // adding new patient if pesel doesn't exist
    @Transactional
    public void addPatient(PatientDTO dto){

        if(!existsPatientByPesel(dto.getPersonDTO().getPesel()) ){

            Patient patient = Patient.builder()
                    .insurance(dto.getInsurance())
                    .build();

            if(dto.getPersonDTO() != null) {

                Person person = Person.builder()
                        .name(dto.getPersonDTO().getName())
                        .surname(dto.getPersonDTO().getSurname())
                        .pesel(dto.getPersonDTO().getPesel())
                        .gender(dto.getPersonDTO().getGender())
                        .address(dto.getPersonDTO().getAddress())
                        .birthDate(dto.getPersonDTO().getBirthDate())
                        .birthPlace(dto.getPersonDTO().getBirthPlace())
                        .role(dto.getPersonDTO().getRole())
                        .build();

                Integer id = personRepository.save(person).getId();
                patient.setPerson(personRepository.findById(id).orElseThrow(() -> new NoSuchElementException()));
            }

            patientRepository.save(patient);
        }
    }


    //updating a patient in DB
    @Transactional
    public void updatePatient(PatientDTO dto, Long id){

        if (existsPatientById(id)){

            patientRepository.findById(id).ifPresent(p -> {
                p.setInsurance(dto.getInsurance());

                if(dto.getPersonDTO() != null) {

                    Person person = p.getPerson();
                    person.setName(dto.getPersonDTO().getName());
                    person.setSurname(dto.getPersonDTO().getSurname());
                    person.setGender(dto.getPersonDTO().getGender());
                    person.setPesel(dto.getPersonDTO().getPesel());
                    person.setAddress(dto.getPersonDTO().getAddress());
                    person.setBirthDate(dto.getPersonDTO().getBirthDate());
                    person.setBirthPlace(dto.getPersonDTO().getBirthPlace());

                    personRepository.save(person);
                }
                patientRepository.save(p);
            } );

        }
    }


    //deleting a patient in DB
    @Transactional
    public void deletePatient(Long id){

        patientRepository.findById(id).ifPresent(p -> {

            if (p.getPerson() != null){
                personRepository.delete(p.getPerson());
            }
            patientRepository.delete(p);
        });
    }
}
