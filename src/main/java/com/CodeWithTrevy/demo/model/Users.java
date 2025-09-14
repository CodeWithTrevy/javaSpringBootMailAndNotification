package com.CodeWithTrevy.demo.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity(name = "users")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "users_email_unique", columnNames = "email")
        }
)


public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @Column(name = "id",updatable = false)
    private long id;
    @Column(length = 100,columnDefinition = "TEXT")
    private String firstname;
    @NotBlank(message = "Username is mandatory")

    private String username;
    private String lastname;
    @NotBlank(message = "password is required")
    private String password;


    @NotBlank(message = "Email is required")

    private String email;
    private LocalDateTime createdAt;







}
