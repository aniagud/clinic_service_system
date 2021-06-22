package com.wzpo.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDTO {

    private Date date;

    private String name;

    private String type;

    private String results;

    private String description;

    private AppointmentDTO appointmentDTO;
}
