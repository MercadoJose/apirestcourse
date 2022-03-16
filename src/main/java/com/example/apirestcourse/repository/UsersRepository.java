package com.example.apirestcourse.repository;

import java.util.Optional;

import com.example.apirestcourse.entities.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    @Transactional(readOnly = true)
    Optional<Users> findByUsername(String username);

}
