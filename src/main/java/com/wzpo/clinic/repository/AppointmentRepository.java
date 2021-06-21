package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.Patient;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.entity.Appointment;
import com.wzpo.clinic.entity.Doctor;
import com.wzpo.clinic.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

    Optional<Appointment> findOneById(Long id);

    List<Appointment> findAllByPatient(Patient patient);
    List<Appointment> findAllByDoctor(Doctor doctor);

    Optional<Appointment> findOneByDate(Date date);
    Optional<Appointment> findOneByTime(String time);
    Optional<Appointment> findOneByDateAndTime(Date date, String time);

    List<Appointment> findAll();

    long count();
}
