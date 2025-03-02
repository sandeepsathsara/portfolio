package com.example.movie_booking.service;
import com.example.movie_booking.dao.UserDao;
import com.example.movie_booking.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public boolean activateUser(int userId) {
        return userDao.setActiveStatus(userId, true);
    }

    public boolean deactivateUser(int userId) {
        return userDao.setActiveStatus(userId, false);
    }
}
