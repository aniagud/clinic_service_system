package com.wzpo.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Date date;

    private String time;

    private String type;

    private InterviewDTO interviewDTO;

    private PatientDTO patientDTO;

    private DoctorDTO doctorDTO;
}
