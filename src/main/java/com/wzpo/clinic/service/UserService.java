package com.wzpo.clinic.service;

import com.wzpo.clinic.dto.UserDTO;
import com.wzpo.clinic.entity.User;
import com.wzpo.clinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean existsUserById(Integer id) {
        return userRepository.findOneById(id).isPresent();
    }

    public Boolean existsUserByEmail(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }


    public Optional<User> getUserById(Integer id) {
        return userRepository.findOneById(id);
    }

    @Transactional
    public void addUser(UserDTO dto) {

        if (!existsUserByEmail(dto.getEmail())) {
            User user = User.builder().
                    email(dto.getEmail()).password(dto.getPassword()).build(); // should be hashed

            userRepository.save(user);
        }
    }


    @Transactional
    public void updateUser(UserDTO dto, Integer id) {
        if (existsUserById(id)) {

            userRepository.findOneById(id).ifPresent(user -> {
                // TO:DO Update...
                userRepository.save(user);
            });
        }
        ;
    }


    @Transactional
    public void deleteUser(Integer id) {

        userRepository.findOneById(id).ifPresent(user -> {
            userRepository.delete(user);
        });
    }
}
