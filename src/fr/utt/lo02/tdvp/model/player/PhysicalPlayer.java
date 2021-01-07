package fr.utt.lo02.tdvp.model.player;

import fr.utt.lo02.tdvp.controller.Actions;
import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Stack;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.view.cli.Input;

public class PhysicalPlayer extends Player {
    /**
     * Count the number of initialized physical players
     */
    private static int initializedCount = 0;
    
    GameManager gameManager =  GameManager.getInstance();
    	
    public void placeCard() {
    	
    	//Cannot place card anymore
    	int actionIndex = availableOptions.indexOf(Actions.PlaceCard);
    	availableOptions.remove(actionIndex);
    	
    	//Can End Turn
    	availableOptions.add(Actions.EndTurn);
    	
    	//Place Card
    	gameManager.notifyObservers(Events.AskToPlaceCard);    	
    }    

    public void moveCard() {
    	//Cannot move cards anymore
    	int actionIndex = availableOptions.indexOf(Actions.MoveCards);
    	availableOptions.remove(actionIndex); 
    	
    	//Place Card
    	gameManager.notifyObservers(Events.AskToMoveCards);  
    }

    /**
     * Plays a turn
     */
	public void play() {
		Layout layout = gameManager.getLayout();
		
		isTurnOver = false;

        // Draw a card
        drawnCard = Stack.getInstance().drawCard();

        // Display layout
        layout.display();

        // Display drawn card
        System.out.println("Tu viens de piocher : " + drawnCard);
        
        //OPTIONS
    	availableOptions.add(Actions.PlaceCard);
    	availableOptions.add(Actions.SeeVictoryCard);
    	availableOptions.add(Actions.MoveLayout);

        // Ask what action to do if the layout has 2 cards at least
        //int answer = 1;
        
    	if (layout.countCards() > 1) {
        	availableOptions.add(Actions.MoveCards);
        }
        
        while(!isTurnOver)
        {
        	gameManager.notifyObservers(Events.AskPlayerToPlay);
        }

	}

    /**
     * Prompt the name of the player
     */
    protected void initName() {
        
    }
    
    public void setName(String name) {
    	this.name = name;
    }
}
