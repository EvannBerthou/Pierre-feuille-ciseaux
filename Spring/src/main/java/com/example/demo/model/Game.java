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

import lombok.Getter;

@Entity
@Getter
public class Game {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;
    @OneToOne(cascade = CascadeType.ALL) private Tour tour1;
    @OneToOne(cascade = CascadeType.ALL) private Tour tour2;
    private Long winner;
    private boolean isEnded;
    @CreationTimestamp private Date creationDate;

    public Game() {}

    public Game(Long _id) {
        this.id = _id;
    }

    public Game(Long _id, Tour _tour1, Tour _tour2) {
        this.id = _id;
        this.tour1 = _tour1;
        this.tour2 = _tour2;
    }

    public void setTour1(Long player, char play) {
        Tour tour = new Tour(player, play);
        this.tour1 = tour;
    }

    public void setTour2(Long player, char play) {
        Tour tour = new Tour(player, play);
        this.tour2 = tour;
    }

    public void addTour(Long player, char play) {
        if (getTour1() == null) {
            setTour1(player, play);
            // Si c'est le premier tour, alors on ne détermine pas le gagnant
            return;
        }
        else if (getTour2() == null) {
            // Si le même joueur joue les deux coups d'une même partie
            if (getTour1().getPlayer() == player) {
                throw new SamePlayerException(player);
            }
            setTour2(player, play);
        } else {
            throw new GameEndedException(this.getId());
        }

        setGagnant();
    }

    private void setGagnant() {
        char c1 = getTour1().getCoup();
        char c2 = getTour2().getCoup();

        // Egalité
        if (c1 == c2) { 
            this.winner = null;
        }
        // Si le joueur1 a fait un coup gagnant contre le joueur2 alors il gagne
        else if ((c1 == 'p' && c2 == 'c') || (c1 == 'f' && c2 == 'p') || (c1 == 'c' && c2 == 'f')) {
            this.winner = getTour1().getPlayer();
        }
        // Sinon le joueur 2 gagne.
        else {
            this.winner = getTour2().getPlayer();
        }
        this.isEnded = true;
    }

    public void clearTours() {
        this.tour1 = null;
        this.tour2 = null;
    }
}
