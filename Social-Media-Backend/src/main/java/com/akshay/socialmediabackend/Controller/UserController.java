package com.akshay.socialmediabackend.Controller;

import com.akshay.socialmediabackend.Models.User;
import com.akshay.socialmediabackend.Repository.UserRepository;
import com.akshay.socialmediabackend.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserServices userServices;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/")
    public User CreateUser(@RequestBody User user)
    {
        return userServices.registerUser(user);
    }

    @GetMapping("/users/getUsers")
    public List<User> getUsers()
    {

        return userRepository.findAll();
    }
    @GetMapping("/users/{userid}")
    public User getUserById(@PathVariable Integer userid) throws Exception {
        return userServices.findUserById(userid);
    }

    @GetMapping("/users/{email}")
    public User getUserByEmail(@PathVariable String email) throws Exception {
        return userServices.findUserByEmail(email);

    }
    @PutMapping("/users/{userid1}/{userid2}")
    public User followUserHandler(@PathVariable Integer userid1, @PathVariable Integer userid2) throws Exception {
        return userServices.followUser(userid1,userid2);
    }

    @PutMapping("/users/{userid}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userid) throws Exception {

    return userServices.updateUser(user,userid);

    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id) throws Exception {
        return userServices.deleteUser(id);
    }

    @GetMapping("users/search/{query}")
    public List<User> searchUser(@PathVariable("query") String query)
    {
        return userServices.searchUser(query);
    }
}
