package com.example.demo.model;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> { 
    List<Game> findByIsEnded(@Param("isEnded") boolean hasEnded);
    List<Game> findByOrderByCreationDateAsc();

    @Query(value = "SELECT COUNT(*) FROM game WHERE winner = (SELECT id FROM user WHERE username=:username)", nativeQuery = true)
    Integer countUserWin(@Param("username") String username);

    @Query(value =
            "SELECT COUNT(*) FROM game,tour WHERE tour.id IN (game.tour1_id, game.tour2_id)" +
            "AND game.winner IS NOT NULL " +
            "AND (SELECT id from user where username=:username) != game.winner " +
            "AND (SELECT id FROM user WHERE username=:username) =  tour.player",
           nativeQuery = true)
    Integer countUserLose(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM tour WHERE player = (SELECT id FROM user WHERE username=:username)", nativeQuery = true)
    Integer countTotalGame(@Param("username") String username);

    @Query(value =
            "SELECT * FROM game WHERE (SELECT id FROM user WHERE username=:username) IN " +
            "(SELECT tour.player FROM tour WHERE tour.id IN (game.tour1_id, game.tour2_id))",
            nativeQuery = true)
    List<Game> findAllPlayedBy(@Param("username") String username);
}
