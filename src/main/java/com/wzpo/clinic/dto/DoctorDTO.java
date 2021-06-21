package com.wzpo.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private String speciality;

    private PersonDTO personDTO;
}
