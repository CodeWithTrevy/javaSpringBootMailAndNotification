package com.CodeWithTrevy.demo.controller;


import com.CodeWithTrevy.demo.model.Users;
import com.CodeWithTrevy.demo.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path="api/users")
public class UserController {
    private  final UserServices userServices;
    @Autowired

    public UserController(UserServices userServices){
        this.userServices = userServices;

    }
    @GetMapping("/allUsers")
    public Page<Users> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return userServices.getUsers(page, size);
    }
    @PostMapping(path = "addUser")
    public ResponseEntity<String>  addUser(@Valid @RequestBody Users users){
        this.userServices.addUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body("Users added sucessfully");


    }
    @DeleteMapping(path = "delete/{userId}")

    public void deleteUser(@PathVariable("userId") Long id){
        this.userServices.deleteUser(id);

    }

    @PutMapping(path="updateUser/{userId}")
    public void upDateUser(
            @PathVariable("userId") Long id,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDateTime createdAt) {

        userServices.upDateUser(id, firstname, username, lastname, email, createdAt);
    }




}
