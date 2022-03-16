package com.example.apirestcourse.services.interfaces;

import java.util.List;

import com.example.apirestcourse.dto.UserRequest;
import com.example.apirestcourse.dto.UsersDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUsersService {

    List<UsersDTO> findAll();

    UsersDTO findByUsername(String userName);

    UsersDTO findByUserId(int userId);

    void save(UserRequest user);

    void saveAll(List<UserRequest> users);

    void deleteById(int userId);

    void update(UserRequest user, int userId);
}
