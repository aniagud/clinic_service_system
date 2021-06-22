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
@Table(name = "examination", schema = "clinic")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "results")
    private String results;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "appointment")
    private Appointment appointment;
}
