package com.CodeWithTrevy.demo.controller;


import com.CodeWithTrevy.demo.model.Posts;
import com.CodeWithTrevy.demo.model.Users;
import com.CodeWithTrevy.demo.services.ExternalApiService;
import com.CodeWithTrevy.demo.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping(path="api/users")
public class UserController {
    private  final UserServices userServices;
    private  final ExternalApiService externalApiService;


    @Autowired

    public UserController(UserServices userServices, ExternalApiService externalApiService){
        this.userServices = userServices;

        this.externalApiService = externalApiService;
    }
    @GetMapping("/allUsers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")


    public Page<Users> getUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        Sort sort;

        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        return userServices.getUsers(pageNumber, pageSize, sort);

    }


    @GetMapping("/home")

    public String home(){
        return "hello world!";

    }




    // make an api request to the service

//    @GetMapping("/callClient")
//    private String getContent(){
//        String uri= "http://localhost:8080/api/users/home";
//
//        RestTemplate restTemplate =  new RestTemplate();
//        String  result = restTemplate.getForObject(uri,String.class);
//
//        return  result;
//
//    }

    @GetMapping(value = "/cityWeather/{city}")

    public String getCityWeather(@PathVariable String city){
        return  externalApiService.fetchDataFromExternalApi(city);







////        String uri = "https://restcountries.com/v3.1/all";
//
//        String uri = "https://restcountries.com/v3.1/independent?status=true";
//
//
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        Object [] countries = restTemplate.getForObject(uri,Object[].class);
//
//        return Arrays.asList(countries);
    }








    @PostMapping(path = "addUser")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String>  addUser(@Valid @RequestBody Users users){
        this.userServices.addUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body("Users added sucessfully");


    }
    @DeleteMapping(path = "delete/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

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
