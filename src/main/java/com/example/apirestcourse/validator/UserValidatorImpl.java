package com.example.apirestcourse.validator;

import com.example.apirestcourse.dto.UserRequest;
import com.example.apirestcourse.utils.exceptions.ApiUnprocessableEntity;

import org.springframework.stereotype.Component;

@Component
public class UserValidatorImpl implements UserValidator {

    @Override
    public void validator(UserRequest request) throws ApiUnprocessableEntity {
        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            this.message("El nombre es obligatorio");
        }
        if (request.getFirstName().length() < 3) {
            this.message("El nombre es muy corto, debe tener minimo 3 caracteres");
        }
        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            this.message("El apellido es obligatorio");
        }
        if (request.getLastName().length() < 3) {
            this.message("El apellido es muy corto, debe tener minimo 3 caracteres");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            this.message("La contrasena es obligatoria");
        }
        if (request.getPassword().length() < 8) {
            this.message("La contrasena es muy corta, debe tener minimo 8 caracteres");
        }
        if (request.getUserName() == null || request.getUserName().isEmpty()) {
            this.message("El nombre de usuario es obligatorio");
        }
        if (request.getUserName().length() < 6) {
            this.message("El nombre de usuario es muy corto, debe tener minimo 6 caracteres");
        }
    }

    private void message(String message) throws ApiUnprocessableEntity {
        throw new ApiUnprocessableEntity(message);
    }

}
