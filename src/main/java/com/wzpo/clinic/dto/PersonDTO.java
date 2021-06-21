package com.wzpo.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String name;

    private String surname;

    private String gender;

    private String pesel;

    private String address;

    private String birthDate;

    private String birthPlace;

    private String role;
}
