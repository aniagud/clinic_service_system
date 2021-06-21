package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.Patient;
import com.wzpo.clinic.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long>{

    Optional<Patient> findOneById(Long id);
    Optional<Patient> findOneByPerson(Person person);

    Optional<Patient> findOneByPerson_Id(Integer id);
    List<Patient> findAllByPerson_Name(String name);
//    List<Patient> findAllByPerson_NameAndPerson_RoleLikePatient(String name);
    List<Patient> findAllByPerson_Surname(String surname);
    List<Patient> findAllByPerson_Gender(String gender);
    Optional<Patient> findOneByPerson_Pesel(String pesel);

    List<Patient> findAll();

    long count();

}
