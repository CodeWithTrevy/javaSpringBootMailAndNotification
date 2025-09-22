package com.CodeWithTrevy.demo.services;

import com.CodeWithTrevy.demo.dao.PostsRepository;
import com.CodeWithTrevy.demo.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PostsServices {
    private PostsRepository postsRepository;

    @Autowired
    public PostsServices(PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }
    public Page<Posts>getPosts(int pageNumber, int pageSize, Sort sort, String search) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        if(search == null || search.isEmpty()){
            return postsRepository.findAll(pageable);
        } else {
            return postsRepository.findByAuthorId(Long.valueOf(search), pageable);
        }



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
