package com.example.crashcourse.controller;


import com.example.crashcourse.entity.User;
import com.example.crashcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> allUser = userService.getAll();
        return new ResponseEntity<>(allUser, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User newUser){
        userService.saveUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //request body username and password is send
    @PutMapping("{username}")
    public ResponseEntity<?> editUser(@PathVariable String username,@RequestBody User user){
        User userInDb = userService.findByUsername(username);
        if(userInDb !=null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
