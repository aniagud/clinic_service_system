package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{

    Optional<Person> findOneByName(String name);

    Optional<Person> findOneBySurname(String surname);

    List<Person> findAll();

    Optional<Person> findById(Integer id);
}
