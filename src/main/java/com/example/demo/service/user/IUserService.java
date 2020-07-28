package com.example.demo.service.user;

import com.example.demo.model.User;
import com.example.demo.service.IService;

public interface IUserService {
    User getUserByUserName(String userName);
}
