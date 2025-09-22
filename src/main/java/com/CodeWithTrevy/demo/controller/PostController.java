package com.CodeWithTrevy.demo.controller;

import com.CodeWithTrevy.demo.model.Posts;
import com.CodeWithTrevy.demo.services.PostsServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostsServices postsServices;

    @Autowired
    public PostController(PostsServices postsServices){
        this.postsServices = postsServices;
    }


    @GetMapping("/allPosts")
    public Page<Posts> getPosts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort;

        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        return postsServices.getPosts(pageNumber, pageSize, sort,search);
    }





    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@Valid @RequestBody Posts post){
        postsServices.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post added successfully");
    }


    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long id){
        postsServices.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }


    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<Posts> updatePost(@PathVariable("postId") Long id, @RequestBody Posts updatedPost){
        Posts post = postsServices.updatePost(id, updatedPost);
        return ResponseEntity.ok(post);
    }
}
