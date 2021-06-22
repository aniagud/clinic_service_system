package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.Doctor;
import com.wzpo.clinic.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable,Long>{

    Optional<Timetable> findOneById(Long id);

    List<Timetable> findAllByDoctor(Doctor doctor);
    List<Timetable> findAllByDoctor_Id(Long id);


    List<Timetable> findAllByDate(Date date);

    List<Timetable> findAll();

}
