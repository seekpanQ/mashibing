package com.example.springbootanotation.service;

import com.example.springbootanotation.dao.UserDao;
import com.example.springbootanotation.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }
}
