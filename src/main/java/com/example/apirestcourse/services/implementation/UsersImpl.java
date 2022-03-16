package com.example.apirestcourse.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.apirestcourse.dto.UserRequest;
import com.example.apirestcourse.dto.UsersDTO;
import com.example.apirestcourse.entities.Users;
import com.example.apirestcourse.repository.UsersRepository;
import com.example.apirestcourse.services.interfaces.IUsersService;
import com.example.apirestcourse.utils.hash.BCrypt;
import com.example.apirestcourse.utils.helpers.MHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersImpl implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersDTO> findAll() {
        List<UsersDTO> dto = new ArrayList<>();

        Iterable<Users> users = this.usersRepository.findAll();

        for (Users user : users) {
            UsersDTO usersDTO = MHelpers.modelMapper().map(user, UsersDTO.class);
            dto.add(usersDTO);
        }

        return dto;
    }

    @Override
    public UsersDTO findByUsername(String userName) {
        Optional<Users> users = this.usersRepository.findByUsername(userName);
        if (!users.isPresent()) {
            return null;
        }
        return MHelpers.modelMapper().map(users, UsersDTO.class);
    }

    @Override
    public UsersDTO findByUserId(int userId) {
        Optional<Users> users = this.usersRepository.findById(userId);
        if (!users.isPresent()) {
            return null;
        }
        return MHelpers.modelMapper().map(users, UsersDTO.class);
    }

    @Override
    public void save(UserRequest user) {

        Users users = MHelpers.modelMapper().map(user, Users.class);

        users.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        this.usersRepository.save(users);
    }

    @Override
    public void saveAll(List<UserRequest> users) {
        List<Users> u = new ArrayList<>();
        for (UserRequest user : users) {
            Users us = MHelpers.modelMapper().map(user, Users.class);
            u.add(us);
        }
        this.usersRepository.saveAll(u);
    }

    @Override
    public void deleteById(int userId) {
        this.usersRepository.deleteById(userId);
    }

    @Override
    public void update(UserRequest request, int userId) {

        Optional<Users> users = this.usersRepository.findById(userId);

        Users user = users.get();
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setUsername(request.getUserName());

        if (request.getPassword().isEmpty()) {

            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

        }

    }

}
