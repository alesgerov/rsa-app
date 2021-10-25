package com.example.rsaproject.repository;

import com.example.rsaproject.entity.CardTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardTable,Long> {
    @Override
    Optional<CardTable> findById(Long aLong);
    @Query("select c from CardTable c where c.card_number=:card_number")
    Optional<CardTable> findByCard_number(@RequestParam("card_number") String card_number);
}
