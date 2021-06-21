package com.wzpo.clinic.controller;

import com.wzpo.clinic.dto.InterviewDTO;
import com.wzpo.clinic.dto.PersonDTO;
import com.wzpo.clinic.entity.Interview;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.service.InterviewService;
import com.wzpo.clinic.service.PersonService;
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
@RequestMapping(value = "api/interview")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService){
        this.interviewService = interviewService;
    }

    //Getting a list

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Interview> getAllInterview(){
        return interviewService.getAllInterview();
    }


    //Searching for an interview

    @GetMapping("/id/{id}")
    public Interview showInterviewById(@PathVariable("id") Long id){

        Interview interview = interviewService.getInterviewById(id)
                .orElseThrow( () -> new IllegalArgumentException("Invalid interview id: " + id));

        return interview;
    }


    //Adding a new interview

    @PostMapping(value = "/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InterviewDTO> addInterview(@RequestBody final InterviewDTO interviewDTO){

        try{
            interviewService.addInterview(interviewDTO);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to add to a database. Exception caused by an interview: {0}", interviewDTO.getDescription()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    //Updating an interview data

    @PutMapping("/update/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Interview> updateInterview(@PathVariable("id") Long id, @RequestBody Interview interview){

        try{
            interviewService.updateInterview(interview);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to update in database an interview with id {0} and description: {1}",id,interview.getDescription()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Deleting an interview

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Interview> deleteInterview(@PathVariable("id") Long id){

        Interview interview = interviewService.getInterviewById(id)
                .orElseThrow( () -> new IllegalArgumentException("Invalid interview id: " + id));

        try{
            interviewService.deleteInterview(interview);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error(MessageFormat.format("Exception while trying to delete in database an interview with id {0} and description: {1}",id,interview.getDescription()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
