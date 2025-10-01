package com.CodeWithTrevy.demo.repository;

import com.CodeWithTrevy.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);


    @Query("SELECT COUNT(u) FROM users u WHERE u.createdAt > :date")
    long countUsersAfter(@Param("date") LocalDateTime date);


//    long countByCreatedAtAfter(LocalDateTime date);

    Optional<Users> findUserByEmail(String email);


}
