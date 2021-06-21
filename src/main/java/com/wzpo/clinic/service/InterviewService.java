package com.wzpo.clinic.service;

import com.wzpo.clinic.dto.InterviewDTO;
import com.wzpo.clinic.dto.PersonDTO;
import com.wzpo.clinic.entity.Interview;
import com.wzpo.clinic.entity.Person;
import com.wzpo.clinic.repository.InterviewRepository;
import com.wzpo.clinic.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository){
        this.interviewRepository = interviewRepository;
    }

    public List<Interview> getAllInterview(){
        return interviewRepository.findAll();
    }

    public Optional<Interview> getInterviewById(Long id){ return interviewRepository.findById(id); }

    public Boolean existsInterviewById(Long id) { return interviewRepository.findById(id).isPresent(); }


    // adding a new interview
    @Transactional
    public void addInterview(InterviewDTO dto){

        interviewRepository.save(Interview.builder()
                .description(dto.getDescription())
                .recommendation(dto.getRecommendation())
                .build());
    }


    //updating an interview in DB
    @Transactional
    public void updateInterview(Interview interview){

        if (existsInterviewById(interview.getId())){

            interviewRepository.save(interview);
        }
    }


    //deleting an interview in DB
    @Transactional
    public void deleteInterview(Interview interview){

        if(existsInterviewById(interview.getId())){

            interviewRepository.delete(interview);
        }
    }
}
