package com.wzpo.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person", schema = "clinic")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "address")
    private String address;

    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "birthPlace")
    private String birthPlace;

    @Column(name = "role")
    private String role;

}
