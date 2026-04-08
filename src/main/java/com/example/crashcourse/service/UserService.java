package com.example.crashcourse.service;

import com.example.crashcourse.entity.User;
import com.example.crashcourse.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId Id){
        return userRepository.findById(Id);
    }

    public void deleteUser(ObjectId Id){
        userRepository.deleteById(Id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }




}
