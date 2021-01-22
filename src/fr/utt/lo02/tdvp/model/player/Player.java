package fr.utt.lo02.tdvp.model.player;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.tdvp.controller.Actions;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.Stack;

/**
 * The Player class, that stores all the player infos and defines all the different abstract methods
 **/

public abstract class Player {
	
	/**
	 * The Players name
	 **/
    protected String name;

    /**
     * The Player's score
     **/
    protected int score = 0;

    /**
     * The Player's victory card
     **/
    protected Card victoryCard;
    
    /**
     * The last card the player has drawn
     **/
    protected Card drawnCard;
    
    /**
     * The Player's available actions, stored as a List of Actions (of Enum type)
     **/
    protected List<Actions> availableOptions = new ArrayList<Actions>();
    
    /**
     * Tells if the turn is over or not
     **/
    protected boolean isTurnOver;
    
    
    /**
     * @return the last drawn Card by the player
     **/
    public Card getDrawnCard() {
    	return this.drawnCard;
    }
    
    /**
     * End the player's Turn
     **/
    public void endTurn()
    {
    	for(int i = 0; i< availableOptions.size(); i++)
    	{
    		availableOptions.remove(i);
    	}
    	
    	isTurnOver = true;
    }
    
    /**
     * @return the available options for the player. Tells what the player can do 
     **/
    public List<Actions> getAvailableOptions(){
    	return this.availableOptions;	
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

    /**
     * Determines the player play turn
     * Ask the options for the physical player
     * Call the play method for the IA
     **/
    public abstract void play();
    
    /**
     * Ask the player to place a card
     **/
    public abstract void placeCard();
    
    /**
     * Ask the player to move a card
     **/
    public abstract void moveCard();
    
    /**
     * Set the player's Name 
     * @param the given name for this player
     **/
    public abstract void setName(String name);
}
