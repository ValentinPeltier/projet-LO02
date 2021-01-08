package fr.utt.lo02.tdvp.model.player;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.model.Stack;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.layout.Location;

public class VirtualPlayerEasy extends VirtualPlayer {


    /**
     * Play a turn with a low level strategy
     */
    public void play() {

    	int choice, choice2;
    	Card drawnCard = Stack.getInstance().drawCard();

    	//Place Card ?
        choice = randomInt(0,1);
        // YES
    	if(choice == 1) {
    		moveCard();
    		placeCard(drawnCard);
    	}
    	else {
    		placeCard(drawnCard);
    		choice2 = randomInt(0,1);
    		if(choice2 == 1) {
    			moveCard();
    		}
    	}

    	System.out.println("### " + this.name + " a fini de jouer ! ###\n");
    }

    public void moveCard() {
        Layout layout = Settings.getInstance().getLayout();

        //1st card to move
        List<Location> possibleLocationsSource = new ArrayList<Location>();

    	int xMax = layout.getX();
    	int yMax = layout.getY();

    	for(int x = 0; x < xMax; x++) {
    		for(int y = 0; y < yMax; y++) {
    			//LOCATION HAS TO BE NON NULL
        		if(layout.getLocations().containsKey(new Location(x,y)) && layout.getCardAt(x, y) != null) {
        			possibleLocationsSource.add(new Location(x,y));
        		}
        	}
    	}

    	//Pickup a location randomly for the first Card
		int tryLocationIndex = randomInt(0,((int) (possibleLocationsSource.size()-1)));
		Location tryLocationSource = possibleLocationsSource.get(tryLocationIndex);

		int x1 = tryLocationSource.getX();
		int y1 = tryLocationSource.getY();


		//2nd location
        List<Location> possibleLocationsDestination = new ArrayList<Location>();

    	for(int x = 0; x < xMax; x++) {
    		for(int y = 0; y < yMax; y++) {
    			//LOCATION MUST NOT BE THE SAME AS THE SOURCE
        		if(layout.getLocations().containsKey(new Location(x,y)) && layout.getCardAt(x, y) != layout.getCardAt(x1,y1)) {
        			possibleLocationsDestination.add(new Location(x,y));
        		}
        	}
    	}


        // Ask for the destination
        while (true) {
        	//Pickup a location randomly for the second Card
    		tryLocationIndex = randomInt(0, (int)(possibleLocationsSource.size() - 1));
    		Location tryLocationDestination = possibleLocationsSource.get(tryLocationIndex);

    		int x2 = tryLocationDestination.getX();
    		int y2 = tryLocationDestination.getY();

            boolean error = false;

            // Is there a card at the destination coordinates ?
            if (layout.getCardAt(x2, y2) == null) {
                // Remove the card
                Card originCard = layout.getCardAt(x1, y1);
                layout.setCardAt(x1, y1, null);

                // Check if the destination has adjacent cards
                if (
                    layout.getCardAt(x2 - 1, y2) == null
                    && layout.getCardAt(x2 + 1, y2) == null
                    && layout.getCardAt(x2, y2 - 1) == null
                    && layout.getCardAt(x2, y2 + 1) == null
                ) {
                    error = true;
                }

                // Replace the card
                layout.setCardAt(x1, y1, originCard);
            }

            if (!error) {
                if(layout.moveCard(x1, y1, x2, y2)) {
                    // Card has been moved, we are done
                	System.out.println(this.name + " a deplace des cartes\n");
                    return;
                }
                else {
                    GameManager.getInstance().notifyObservers(Events.LogError);
                }
            }

            possibleLocationsDestination.remove(tryLocationIndex);
        }
    }

    private void placeCard(Card card) {
    	Layout layout = Settings.getInstance().getLayout();
    	List<Location> possibleLocations = new ArrayList<Location>();

    	int xMax = layout.getX();
    	int yMax = layout.getY();

    	for(int x = 0; x < xMax; x++) {
    		for(int y = 0; y < yMax; y++) {
        		if(layout.getLocations().containsKey(new Location(x,y)) && layout.getCardAt(x, y) == null) {
        			possibleLocations.add(new Location(x,y));
        		}
        	}
    	}

    	while(true) {
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
                //ERROR, let's retry
            }
            else if (layout.placeCard(tryLocation.getX(), tryLocation.getY(), card)) {
                // Card has been placed, we are done
            	System.out.println(this.name+" a place une carte\n");
                return;
            }
            else {
            	GameManager.getInstance().notifyObservers(Events.LogError);
            }

            //REMOVE
            possibleLocations.remove(tryLocationIndex);
    	}
    }

    private int randomInt(int Min,int Max) {
    	int random = Min + (int)(Math.random() * ((Max - Min) + 1));
    	return random;
    }

	@Override
	public void setName(String name) {}

	@Override
	public void placeCard() {
		// TODO Auto-generated method stub

	}
}
