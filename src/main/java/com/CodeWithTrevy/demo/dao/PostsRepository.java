package com.CodeWithTrevy.demo.dao;

import com.CodeWithTrevy.demo.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    Optional<Posts> findByTitle(String title);
}
