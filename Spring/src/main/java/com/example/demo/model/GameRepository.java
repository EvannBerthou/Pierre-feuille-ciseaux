package com.example.demo.model;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends CrudRepository<Game, Long> { 
    List<Game> findByHasEnded(@Param("hasEnded") boolean hasEnded);
    List<Game> findByOrderByCreationDateAsc();
}
