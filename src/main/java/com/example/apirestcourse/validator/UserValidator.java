package com.example.apirestcourse.validator;

import com.example.apirestcourse.dto.UserRequest;
import com.example.apirestcourse.utils.exceptions.ApiUnprocessableEntity;

import org.springframework.stereotype.Service;

// Interfaz de la validacion de datos recibidos para
// la creacion de usuarios

@Service
public interface UserValidator {

    void validator(UserRequest userRequest) throws ApiUnprocessableEntity;

}
