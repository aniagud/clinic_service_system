package com.wzpo.clinic.service;


import com.wzpo.clinic.dto.DoctorDTO;
import com.wzpo.clinic.entity.Doctor;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.repository.DoctorRepository;
import com.wzpo.clinic.repository.PersonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PersonRepository personRepository;

    public DoctorService(DoctorRepository doctorRepository,
                          PersonRepository personRepository){
        this.doctorRepository = doctorRepository;
        this.personRepository = personRepository;
    }


    public List<Doctor> getAllDoctor(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id){
        return doctorRepository.findOneById(id);
    }

    public Optional<Doctor> getDoctorByPerson(Person person){
        return doctorRepository.findOneByPerson(person);
    }

    public List<Doctor> getDoctorByName(String name){
        return doctorRepository.findAllByPerson_Name(name);
    }

    public List<Doctor> getDoctorBySurname(String surname){
        return doctorRepository.findAllByPerson_Surname(surname);
    }

    public Optional<Doctor> getDoctorByPesel(String pesel){
        return doctorRepository.findOneByPerson_Pesel(pesel);
    }

    public List<Doctor> getDoctorByGender(String gender){
        return doctorRepository.findAllByPerson_Gender(gender);
    }

    public List<Doctor> getDoctorBySpeciality(String speciality){
        return doctorRepository.findAllBySpeciality(speciality);
    }


    public Boolean existsDoctorByPesel(String pesel){
        return doctorRepository.findOneByPerson_Pesel(pesel).isPresent();
    }

    public Boolean existsDoctorById(Long id) { return doctorRepository.findById(id).isPresent(); }


    // adding new doctor if pesel doesn't exist
    @Transactional
    public void addDoctor(DoctorDTO dto){

        if(!existsDoctorByPesel(dto.getPersonDTO().getPesel()) ){

            Doctor doctor = Doctor.builder()
                    .speciality(dto.getSpeciality())
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
                doctor.setPerson(personRepository.findById(id).orElseThrow(() -> new NoSuchElementException()));
            }

            doctorRepository.save(doctor);
        }
    }


    //updating a doctor in DB
    @Transactional
    public void updateDoctor(DoctorDTO dto, Long id){

        if (existsDoctorById(id)){

            doctorRepository.findById(id).ifPresent(d -> {
                d.setSpeciality(dto.getSpeciality());

                if(dto.getPersonDTO() != null) {

                    Person person = d.getPerson();
                    person.setName(dto.getPersonDTO().getName());
                    person.setSurname(dto.getPersonDTO().getSurname());
                    person.setGender(dto.getPersonDTO().getGender());
                    person.setPesel(dto.getPersonDTO().getPesel());
                    person.setAddress(dto.getPersonDTO().getAddress());
                    person.setBirthDate(dto.getPersonDTO().getBirthDate());
                    person.setBirthPlace(dto.getPersonDTO().getBirthPlace());

                    personRepository.save(person);
                }
                doctorRepository.save(d);
            } );

        }
    }


    //deleting a doctor in DB
    @Transactional
    public void deleteDoctor(Long id){

        doctorRepository.findById(id).ifPresent(p -> {

            if (p.getPerson() != null){
                personRepository.delete(p.getPerson());
            }
            doctorRepository.delete(p);
        });
    }


}
