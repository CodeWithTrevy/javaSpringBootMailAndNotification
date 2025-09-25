package com.CodeWithTrevy.demo.repository;

import com.CodeWithTrevy.demo.model.BirthDayCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BirthDayCardsRepository extends JpaRepository<BirthDayCards, Long> {

    @Query(value = "SELECT * FROM birthdayCards b WHERE EXTRACT(MONTH FROM b.dateofbirth) = :month AND EXTRACT(DAY FROM b.dateofbirth) = :day",
            nativeQuery = true)
    List<BirthDayCards> findCardsWithBirthdayToday(@Param("month") int month,
                                                   @Param("day") int day);

}

