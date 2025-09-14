package com.CodeWithTrevy.demo.controller;

import com.CodeWithTrevy.demo.model.Posts;
import com.CodeWithTrevy.demo.model.Users;
import com.CodeWithTrevy.demo.services.PostsServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "api/posts")
public class PostController {
    private PostsServices postsServices;

    @Autowired

    public PostController(PostsServices postsServices){
        this.postsServices=postsServices;
    }

    @GetMapping("/allPosts")
    public Page<Posts> getPosts(
            @RequestParam(defaultValue= "0") int page,
            @RequestParam(defaultValue= "5") int size)
    {
        return postsServices.getPosts(page,size);


    }
    @PostMapping(path = "addPost")
    public ResponseEntity<String> addPost(@Valid @RequestBody Posts posts){
        this.postsServices.addPost(posts);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post added sucessfully");


    }
    @DeleteMapping(path = "delete/{postId}")

    public void deletePost(@PathVariable("postId") Long id){
        this.postsServices.deletePost(id);

    }

    @PutMapping(path = "updatePost/{postId}")
    public Posts updatePost(
            @PathVariable("postId") Long id,
            @RequestBody Posts updatedPost) {

        return postsServices.updatePost(id, updatedPost);
    }


}
