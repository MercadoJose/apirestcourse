package com.example.apirestcourse.controllers;

import com.example.apirestcourse.dto.UserRequest;
import com.example.apirestcourse.services.interfaces.IUsersService;
import com.example.apirestcourse.utils.exceptions.ApiUnprocessableEntity;
import com.example.apirestcourse.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class ApiController {

    @Autowired
    private IUsersService iUsersService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(this.iUsersService.findAll());
    }

    @GetMapping(value = "/by/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUsername(@PathVariable("username") String userName) {
        return ResponseEntity.ok(this.iUsersService.findByUsername(userName));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUser(@RequestBody UserRequest request) throws ApiUnprocessableEntity {

        this.userValidator.validator(request);
        this.iUsersService.save(request);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/{userId}/delete")
    public ResponseEntity<Object> deleteUser(@PathVariable int userId) {

        this.iUsersService.deleteById(userId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping(value = "/{userId}/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserRequest request, @PathVariable int userId) {

        this.iUsersService.update(request, userId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
