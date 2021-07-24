package com.example.demo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.demo.exception.GameEndedException;
import com.example.demo.exception.SamePlayerException;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Game {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;
    @OneToOne(cascade = CascadeType.ALL) private Tour player1;
    @OneToOne(cascade = CascadeType.ALL) private Tour player2;
    private Long winner;
    private boolean hasEnded = false;
    @CreationTimestamp private Date creationDate;

    public Game() {}

    public Game(Long _id) {
        this.id = _id;
    }

    public Game(Long _id, Tour _player1, Tour _player2) {
        this.id = _id;
        this.player1 = _player1;
        this.player2 = _player2;
    }

    public Long getId() { return this.id; }
    public Tour getPlayer1() { return this.player1; }
    public Tour getPlayer2() { return this.player2; }
    public Long getGagnant() { return this.winner; }
    public boolean getHasEnded() { return this.hasEnded; }
    public Date getCreationDate() { return this.creationDate; }

    public void setPlayer1(Long player, char play) {
        Tour tour = new Tour(player, play);
        this.player1 = tour;
    }

    public void setPlayer2(Long player, char play) {
        Tour tour = new Tour(player, play);
        this.player2 = tour;
    }

    public void addTour(Long player, char play) {
        if (getPlayer1() == null) {
            setPlayer1(player, play);
            // Si c'est le premier tour, alors on ne détermine pas le gagnant
            return;
        }
        else if (getPlayer2() == null) {
            // Si le même joueur joue les deux coups d'une même partie
            if (getPlayer1().getPlayer() == player) {
                throw new SamePlayerException(player);
            }
            setPlayer2(player, play);
        } else {
            throw new GameEndedException(this.getId());
        }

        setGagnant();
    }

    private void setGagnant() {
        char c1 = getPlayer1().getCoup();
        char c2 = getPlayer2().getCoup();

        // Egalité
        if (c1 == c2) { 
            this.winner = null;
        }
        // Si le joueur1 a fait un coup gagnant contre le joueur2 alors il gagne
        else if ((c1 == 'p' && c2 == 'c') || (c1 == 'f' && c2 == 'p') || (c1 == 'c' && c2 == 'f')) {
            this.winner = getPlayer1().getPlayer();
        }
        // Sinon le joueur 2 gagne.
        else {
            this.winner = getPlayer2().getPlayer();
        }
        this.hasEnded = true;
    }
}
