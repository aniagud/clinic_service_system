package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.Doctor;
import com.wzpo.clinic.entity.Examination;
import com.wzpo.clinic.entity.Appointment;
import com.wzpo.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination,Long>{

    Optional<Examination> findOneById(Long id);

    List<Examination> findAllByAppointment(Appointment appointment);
    List<Examination> findAllByAppointment_Patient(Patient patient);
    List<Examination> findAllByAppointment_Patient_Id(Long id);
    List<Examination> findAllByAppointment_Doctor(Doctor doctor);
    List<Examination> findAllByAppointment_Doctor_Id(Long id);


    List<Examination> findAllByDate(Date date);
    List<Examination> findAllByType(String type);

    List<Examination> findAll();

    long count();
}
