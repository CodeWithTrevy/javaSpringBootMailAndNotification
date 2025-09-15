package com.CodeWithTrevy.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Posts")
@Table(
        name = "Posts",
        uniqueConstraints = {
                @UniqueConstraint(name = "author_id_unique", columnNames = "author_id")
        }

)

public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_sequence")
    @SequenceGenerator(
            name = "post_id_sequence",
            sequenceName = "post_id_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @Column(name = "id",updatable = false)
    private long id;
    @Column(name = "title", columnDefinition = "TEXT")
    private String title;
    @NotBlank(message = "post content is required")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    private int author_id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    public Posts(){

    }



    public Posts(long id, String title, String content, int author_id, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public LocalDateTime getCreated_at() {

        return createdAt;
    }

    public LocalDateTime getUpdated_at() {

        return updatedAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setCreated_at(LocalDateTime created_at) {

        this.createdAt = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {

        this.updatedAt = updated_at;
    }
}
