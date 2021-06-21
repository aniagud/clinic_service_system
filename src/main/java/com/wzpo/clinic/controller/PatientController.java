package com.wzpo.clinic.controller;


import com.wzpo.clinic.dto.PersonDTO;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.service.PersonService;
import com.wzpo.clinic.dto.PatientDTO;
import com.wzpo.clinic.entity.Patient;
import com.wzpo.clinic.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping(value = "api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    //Getting a list

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Patient> getAllPatient(){
        return patientService.getAllPatient();
    }

    @GetMapping("/all/{gender}")
    public List<Patient> getAllPatientByGender(@PathVariable("gender") String gender){
        return patientService.getPatientByGender(gender);
    }


    //Searching for patient

    @GetMapping("/name/{name}")
    public List<Patient> showPatientByName(@PathVariable("name") String name){

        return (patientService.getPatientByName(name));
    }

    @GetMapping("/surname/{surname}")
    public List<Patient> showPatientBySurname(@PathVariable("surname") String surname){

        return(patientService.getPatientBySurname(surname));
    }

    @GetMapping("/pesel/{pesel}")
    public Patient showPatientByPesel(@PathVariable("pesel") String pesel){

        Patient patient = patientService.getPatientByPesel(pesel)
                .orElseThrow( () -> new IllegalArgumentException("Invalid patient's pesel: " + pesel));

        return patient;
    }


    //Adding a new patient

    @PostMapping(value = "/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody final PatientDTO patientDTO){

        try{
            patientService.addPatient(patientDTO);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to add a patient to database for patient {0}", patientDTO.getPersonDTO().getSurname()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Updating a patient

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id,
                                                 @RequestBody PatientDTO dto){

        try {
            patientService.updatePatient(dto,id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to update vocabulary with id {0}",id),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Deleting a patient

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") Long id){

        Patient patient = patientService.getPatientById(id)
                .orElseThrow( () -> new IllegalArgumentException("Invalid patient id: " + id));

        try {
            patientService.deletePatient(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to delete a patient with id {0}",id), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
