package com.example.demo.model;

public class Game {
    private Long id;
    private Tour player1;
    private Tour player2;
    private Long gagnant;

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
    public Long getGagnant() { return this.gagnant; }

    public void setPlayer1(Long player, char play) {
        this.player1 = new Tour(player, play);
    }

    public void setPlayer2(Long player, char play) {
        this.player2 = new Tour(player, play);
    }

    public void addTour(Long player, char play) {
        if (getPlayer1() == null) {
            setPlayer1(player, play);
            // Si c'est le premier tour, alors on ne détermine pas le gagnant
            return;
        }
        else if (getPlayer2() == null) {
            setPlayer2(player, play);
        }

        setGagnant();
    }

    private void setGagnant() {
        char c1 = getPlayer1().getCoup();
        char c2 = getPlayer2().getCoup();

        // Egalité
        if (c1 == c2) this.gagnant = null;
        // Si le joueur1 a fait un coup gagnant contre le joueur2 alors il gagne
        if ((c1 == 'p' && c2 == 'c') || (c1 == 'f' && c2 == 'p') || (c1 == 'c' && c2 == 'f')) 
            this.gagnant = getPlayer1().getPlayer();
        // Sinon le joueur 2 gagne.
        else
            this.gagnant = getPlayer2().getPlayer();
    }

    public boolean hasEnded() {
        return getPlayer1() != null && getPlayer2() != null;
    }
}
