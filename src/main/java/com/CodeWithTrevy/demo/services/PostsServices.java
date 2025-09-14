package com.CodeWithTrevy.demo.services;

import com.CodeWithTrevy.demo.dao.PostsRepository;
import com.CodeWithTrevy.demo.exception.EmailAlreadyExistsException;
import com.CodeWithTrevy.demo.model.Posts;
import com.CodeWithTrevy.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PostsServices {
    private PostsRepository postsRepository;

    @Autowired
    public PostsServices(PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }
    public Page<Posts> getPosts(int page , int size) {
        Pageable pageable = PageRequest.of(page,size);
        return postsRepository.findAll(pageable);
    }
    public Posts addPost(Posts post) {
        Optional<Posts> optionalPost = postsRepository.findByTitle(post.getTitle());
        if (optionalPost.isPresent()) {
            throw new IllegalArgumentException("A post with this title already exists: " + post.getTitle());
        }

        return postsRepository.save(post);
    }
    public void deletePost(Long id){
        if(!postsRepository.existsById(id)){
            throw new  IllegalArgumentException("Post with this id" + id  + "does not exit");
        }
        postsRepository.deleteById(id);

    }
    public Posts updatePost(Long id, Posts updatedPost) {
        Posts existingPost = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + id + " not found."));

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setCreated_at(java.time.LocalDateTime.now());


        return postsRepository.save(existingPost);
    }

}
