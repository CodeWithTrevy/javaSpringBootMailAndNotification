package com.CodeWithTrevy.demo.services;

import com.CodeWithTrevy.demo.repository.UserRepository;
import com.CodeWithTrevy.demo.exception.EmailAlreadyExistsException;
import com.CodeWithTrevy.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public Page<Users> getUsers(int pageNumber, int pageSize, Sort sort) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);

        return userRepository.findAll(pageable);
    }

    public Users addUser(Users users) {
        Optional<Users> optionalUsers = userRepository.findUserByEmail(users.getEmail());
        if (optionalUsers.isPresent()) {
            throw new EmailAlreadyExistsException(users.getEmail());

        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        return userRepository.save(users);
    }
    public boolean checkPassword(String rawPassword,String encodedPassword){
        return passwordEncoder.matches(rawPassword,encodedPassword);

    }

    public void deleteUser(Long id) {
        boolean find = userRepository.existsById(id);
        if (!find) {
            throw new IllegalStateException("user not found");

        }
        userRepository.deleteById(id);

    }


    public void upDateUser(Long id, String firstname, String username, String lastname, String email, LocalDateTime createdAt) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (firstname != null && !firstname.isEmpty()) {
            user.setFirstname(firstname);
        }
        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }
        if (lastname != null && !lastname.isEmpty()) {
            user.setLastname(lastname);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (createdAt != null) {
            user.setCreatedAt(createdAt);
        }

        userRepository.save(user);
    }

}
