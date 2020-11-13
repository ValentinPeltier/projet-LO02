package fr.utt.lo02.tdvp.core;

public abstract class Player {
    private String name;

    private int score;

    private Card victoryCard;

    public String getName() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.name;
    }

    public int getScore() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.score;
    }

    public void setScore(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.score = value;
    }

    public Card getVictoryCard() {
    }

    public abstract void play();

}
