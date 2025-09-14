package com.CodeWithTrevy.demo.dao;

import com.CodeWithTrevy.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUserByEmail(String email);


}
