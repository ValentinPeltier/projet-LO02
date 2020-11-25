package fr.utt.lo02.tdvp.core.player;

import fr.utt.lo02.tdvp.core.Card;
import fr.utt.lo02.tdvp.core.Stack;

public abstract class Player {
    protected String name;

    protected int score = 0;

    protected Card victoryCard;

    /**
     * Initialize the player by drawing a victory card and choosing a name.
     * Stack must be initialized and filled with cards.
     */
    public Player() {
        // Draw a victory card
        this.victoryCard = Stack.getInstance().drawCard();

        // Initialize player's name
        this.initName();
    }

    /**
     * Returns the player's name
     * @return the plauer's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the player's score
     * @return the player's score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Set the player's score.
     * @param value the new player's score. It must be positive otherwise it will be ignored.
     */
    public void setScore(int value) {
        // Check if the score is positive
        if (score >= 0) {
            this.score = value;
        }
    }

    /**
     * Returns the player's victory card
     * @return the player's victory card
     */
    public Card getVictoryCard() {
        return this.victoryCard;
    }

    protected abstract void initName();

    public abstract void play();
}
