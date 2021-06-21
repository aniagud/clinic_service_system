package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview,Long>{

    List<Interview> findAll();

    Optional<Interview> findById(Long id);
}
