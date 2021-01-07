package fr.utt.lo02.tdvp.model.player;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.tdvp.controller.Actions;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.Stack;

public abstract class Player {
    protected String name;

    protected int score = 0;

    protected Card victoryCard;
    
    protected Card drawnCard;
    
    protected List<Actions> availableOptions = new ArrayList<Actions>();
    
    protected boolean isTurnOver;
    
    public Card getDrawnCard() {
    	return this.drawnCard;
    }
    
    public void endTurn()
    {
    	for(int i = 0; i< availableOptions.size(); i++)
    	{
    		availableOptions.remove(i);
    	}
    	
    	isTurnOver = true;
    }
    

    /**
     * Initialize the player by drawing a victory card and choosing a name.
     * Stack must be initialized and filled with cards.
     */
    public Player() {
        // Draw a victory card
        this.drawVictoryCard();

        // Initialize player's name
        this.initName();
    }

    public void drawVictoryCard() {
        // Draw a victory card
        this.victoryCard = Stack.getInstance().drawCard();
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
    
    public abstract void setName(String name);
}
