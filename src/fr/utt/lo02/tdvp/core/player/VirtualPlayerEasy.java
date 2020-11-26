package fr.utt.lo02.tdvp.core.player;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.tdvp.core.Card;
import fr.utt.lo02.tdvp.core.GameManager;
import fr.utt.lo02.tdvp.core.Stack;
import fr.utt.lo02.tdvp.core.layout.Layout;
import fr.utt.lo02.tdvp.core.layout.Location;

public class VirtualPlayerEasy extends VirtualPlayer {
    /**
     * Play a turn with a low level strategy
     */
    public void play() {
    	
    	System.out.println("### A L'IA de jouer ! ###");
    	
    	int choice, choice2;
    	Card drawnCard = Stack.getInstance().drawCard();
    	
    	//Place Card ?
    	choice = randomInt(0,1);
    	if(choice == 1)//YES
    	{
    		moveCard();
    		placeCard(drawnCard);
    	}
    	else
    	{
    		placeCard(drawnCard);
    		choice2 = randomInt(0,1);
    		if(choice2 == 1)
    		{
    			moveCard();
    		}
    	}
    	
    	System.out.println("### L'IA a fini de jouer ! ###");
    	
    	
    }
    
    private void moveCard()
    {
    	//TODO
    }
    
    private void placeCard(Card card)
    {
    	
    	Layout layout = GameManager.getInstance().getLayout();
    	List<Location> possibleLocations = new ArrayList<Location>();
    	
    	
    	int xMax = layout.getX();
    	int yMax = layout.getY();
    	
    	for(int x = 0; x < xMax; x++)
    	{
    		for(int y = 0; y < yMax; y++)
        	{
        		if(layout.getLocations().containsKey(new Location(x,y)) && layout.getCardAt(x, y) == null)
        		{
        			possibleLocations.add(new Location(x,y));
        		}
        	}
    	}
    	
    	while(true)
    	{
    		//Pickup a location randomly
    		int tryLocationIndex = randomInt(0,((int) (possibleLocations.size()-1)));
    		Location tryLocation = possibleLocations.get(tryLocationIndex);
    		
    		
            if (
                !layout.isEmpty()
                && layout.getCardAt(tryLocation.getX() - 1, tryLocation.getY()) == null
                && layout.getCardAt(tryLocation.getX() + 1, tryLocation.getY()) == null
                && layout.getCardAt(tryLocation.getX(), tryLocation.getY() - 1) == null
                && layout.getCardAt(tryLocation.getX(), tryLocation.getY() + 1) == null
            ) {
                //ERREUR
            }
            else if (layout.placeCard(tryLocation.getX(), tryLocation.getY(), card)) {
                // Card has been placed, we are done
            	System.out.println("L'IA a place une carte");
                return;
            }
            else {
                System.out.println("Une erreur est survenue...\n");
            }
            
            //REMOVE 
            possibleLocations.remove(tryLocationIndex);
    	}
    	
    }
    
    
    private int randomInt(int Min,int Max)
    {
    	int random = Min + (int)(Math.random() * ((Max - Min) + 1));
    	return random;
    }
}
