package com.wzpo.clinic.repository;

import com.wzpo.clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneById(Integer id);
    Optional<User> findOneByEmail(String email);

    long count();

}
