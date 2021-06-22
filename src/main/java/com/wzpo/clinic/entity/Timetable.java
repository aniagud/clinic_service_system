package com.wzpo.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timetable", schema = "clinic")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "availability_starts")
    private String availability_starts;

    @Column(name = "availability_ends")
    private String availability_ends;

    @ManyToOne
    @JoinColumn(name = "doctor")
    private Doctor doctor;
}
