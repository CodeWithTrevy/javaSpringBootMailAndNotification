package com.CodeWithTrevy.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity(name = "birthdayCards")

@Table(
        name = "birthdayCards",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)

public class BirthDayCards {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cards_sequence")
    @SequenceGenerator(
            name = "cards_sequence",
            sequenceName = "cards_sequence",
            initialValue = 100,
            allocationSize = 1

    )


    @Column(name = "id" ,updatable = false)
    private long id;

    @Column(name ="firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname",nullable = false)
    private String lastName;
    @Column(name = "dateofbirth",nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "email address is required")
    private String email;


    @Transient
    public int getMonth() {
        return dateOfBirth != null ? dateOfBirth.getMonthValue() : 0;
    }

    @Transient
    public int getDay() {
        return dateOfBirth != null ? dateOfBirth.getDayOfMonth() : 0;
    }
}
