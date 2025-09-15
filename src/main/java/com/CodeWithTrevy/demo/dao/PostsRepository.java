package com.CodeWithTrevy.demo.dao;

import com.CodeWithTrevy.demo.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT COUNT(p) FROM Posts p WHERE p.createdAt > :date")
    long countPostsAfter(@Param("date") LocalDateTime date);
//    long countByCreatedAtAfter(LocalDateTime date);

    Optional<Posts> findByTitle(String title);
}
