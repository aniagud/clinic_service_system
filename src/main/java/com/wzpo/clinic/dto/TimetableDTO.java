package com.wzpo.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO {

    private Date date;

    private String availability_starts;

    private String availability_ends;

    private DoctorDTO doctorDTO;
}
