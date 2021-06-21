package com.wzpo.clinic.controller;
import com.wzpo.clinic.dto.DoctorDTO;
import com.wzpo.clinic.entity.Doctor;
import com.wzpo.clinic.service.DoctorService;
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
@RequestMapping(value = "api/doctor")
public class DoctorController {


    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    //Getting a list

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Doctor> getAllDoctor(){
        return doctorService.getAllDoctor();
    }

    @GetMapping("/all/{gender}")
    public List<Doctor> getAllDoctorByGender(@PathVariable("gender") String gender){
        return doctorService.getDoctorByGender(gender);
    }


    //Searching for Doctor

    @GetMapping("/name/{name}")
    public List<Doctor> showDoctorByName(@PathVariable("name") String name){

        return (doctorService.getDoctorByName(name));
    }

    @GetMapping("/surname/{surname}")
    public List<Doctor> showDoctorBySurname(@PathVariable("surname") String surname){

        return(doctorService.getDoctorBySurname(surname));
    }

    @GetMapping("/pesel/{pesel}")
    public Doctor showDoctorByPesel(@PathVariable("pesel") String pesel){

        Doctor doctor = doctorService.getDoctorByPesel(pesel)
                .orElseThrow( () -> new IllegalArgumentException("Invalid Doctor's pesel: " + pesel));

        return doctor;
    }


    //Adding a new Doctor

    @PostMapping(value = "/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDTO> addDoctor(@RequestBody final DoctorDTO doctorDTO){

        try{
            doctorService.addDoctor(doctorDTO);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to add a doctor to database for doctor {0}", doctorDTO.getPersonDTO().getSurname()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Updating a Doctor

    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") Long id,
                                                 @RequestBody DoctorDTO dto){

        try {
            doctorService.updateDoctor(dto,id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to update Doctor with id {0}",id),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Deleting a Doctor

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") Long id){

        Doctor doctor = doctorService.getDoctorById(id)
                .orElseThrow( () -> new IllegalArgumentException("Invalid Doctor id: " + id));

        try {
            doctorService.deleteDoctor(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to delete a Doctor with id {0}",id), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
