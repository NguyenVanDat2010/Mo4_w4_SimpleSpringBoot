package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository  extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
