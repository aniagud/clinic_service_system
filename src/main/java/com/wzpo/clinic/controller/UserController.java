package com.wzpo.clinic.controller;


import com.wzpo.clinic.dto.UserDTO;
import com.wzpo.clinic.entity.User;
import com.wzpo.clinic.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping(value = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> addUser(@RequestBody final UserDTO userDTO) {

        try {
            userService.addUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            log.error(MessageFormat.format("Exception while trying to add a user to database for user {0}", userDTO.getEmail()), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id,
                                              @RequestBody UserDTO dto) {

        try {
            // TO:DO Do actual update
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error(MessageFormat.format("Exception while trying to update users with id {0}", id), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {

        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));

        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error(MessageFormat.format("Exception while trying to delete a user with id {0}", id), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
