package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.Doctor;
import com.wzpo.clinic.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long>{

    Optional<Doctor> findOneById(Long id);
    Optional<Doctor> findOneByPerson(Person person);

    List<Doctor> findOneByPerson_Id(Integer id);
    List<Doctor> findAllByPerson_Name(String name);
    List<Doctor> findAllByPerson_Surname(String surname);
    List<Doctor> findAllByPerson_Gender(String gender);
    Optional<Doctor> findOneByPerson_Pesel(String pesel);

    List<Doctor> findAll();

    List<Doctor> findAllBySpeciality(String speciality);

    long count();
}
