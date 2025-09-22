package com.CodeWithTrevy.demo.controller;

import com.CodeWithTrevy.demo.model.Posts;
import com.CodeWithTrevy.demo.services.PostsServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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


    @GetMapping("/slicedPosts")


    public ResponseEntity<Map<String,Object>> getSlicedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Slice<Posts> postsSlice=postsServices.getSlicedUsers(page,size);

        Map<String,Object> response = new HashMap<>();

        response.put("content",postsSlice.getContent());
        response.put("hasNext",postsSlice.hasContent());
        response.put("hasPrevious",postsSlice.hasPrevious());
        response.put("size",postsSlice.getSize());
        response.put("numberOfElements", postsSlice.getNumberOfElements());

        return ResponseEntity.ok(response);


    }

//    public Slice<Posts> getSliceUsers(
//            @RequestParam(required = false, defaultValue = "0") int page,
//            @RequestParam(required = false, defaultValue = "10") int size) {
//
//
//
//        return postsServices.getSlicedUsers(page,size);
//    }


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
