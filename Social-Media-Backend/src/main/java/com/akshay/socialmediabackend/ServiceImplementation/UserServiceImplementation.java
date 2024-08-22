package com.akshay.socialmediabackend.ServiceImplementation;

import com.akshay.socialmediabackend.Models.User;
import com.akshay.socialmediabackend.Repository.UserRepository;
import com.akshay.socialmediabackend.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserServices {
    @Autowired
    UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        User newUser= new User();
        newUser.setId(user.getId());
        newUser.setUserName(user.getUserName());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        // first we will check whether the user exists or not for which we will be using  optional
        Optional<User> user=userRepository.findById(userId);

        if(user.isEmpty())
        {
            throw new Exception("User not found");
        }

        return user.get();
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        Optional<User> user =userRepository.findByEmail(email);
         if (user.isEmpty())
         {
             throw new Exception("User not found");
         }

        return user.get();
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1= findUserById(userId1);
        User user2=findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user,Integer userId) throws Exception {
        Optional<User> user1= userRepository.findById(userId);
        if (user1.isEmpty())
        {
            throw new Exception("User not Found");
        }
        User CurrUser= user1.get();

        if (user.getUserName() !=null)
        {
            CurrUser.setUserName(user.getUserName());
        }
        if (user.getName() !=null)
        {
            CurrUser.setName(user.getName());
        }
        if (user.getEmail() != null)
        {
            CurrUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null)
        {
            CurrUser.setPassword(user.getPassword());
        }

        return userRepository.save(CurrUser);
    }

    @Override
    public String deleteUser(Integer id) throws Exception {
        Optional<User> user= userRepository.findById(id);

        if  (user.isEmpty())
        {
            throw new Exception("User not found");
        }
        userRepository.delete(user.get());
        return "User Deleted Successfully";
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
