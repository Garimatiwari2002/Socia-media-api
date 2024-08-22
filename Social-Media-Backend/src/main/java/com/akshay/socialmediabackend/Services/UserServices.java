package com.akshay.socialmediabackend.Services;

import com.akshay.socialmediabackend.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserServices {

    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User followUser(Integer user1, Integer user2) throws Exception;

    public User updateUser(User user, Integer userId) throws Exception;
    public String deleteUser(Integer userId) throws Exception;
    public List<User> searchUser(String query);
}
