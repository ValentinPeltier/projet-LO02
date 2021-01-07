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
    
    Layout layout = GameManager.getInstance().getLayout();
    
    

    private void placeCard(Card card) {
    	
    	//Cannot place card anymore
    	int actionIndex = availableOptions.indexOf(Actions.PlaceCard);
    	availableOptions.remove(actionIndex);
    	
    	GameManager.getInstance().notifyObservers(Events.AskToPlaceCard);    	
    }    

    private void moveCard() {

    	//Cannot place card anymore
    	int actionIndex = availableOptions.indexOf(Actions.MoveCards);
    	availableOptions.remove(actionIndex);
    	
        // Ask for the card to move
        while (true) {
            String response;
            do {
                response = Input.promptString("Quelle carte veux-tu deplacer ? (e.g. \"B2\")");

                if (response.length() != 2) {
                    System.out.println("Emplacement invalide.\n");
                }
            } while(response.length() != 2);
            int x1 = response.charAt(0) - 'A';
            int y1 = response.charAt(1) - '0';

            if (layout.getCardAt(x1, y1) == null) {
                System.out.println("Emplacement invalide.\n");
            }
            else {
                // Ask for the destination
                while (true) {
                    response = Input.promptString("Ou veux-tu deplacer cette carte ? (e.g. \"B2\")");
                    int x2 = response.charAt(0) - 'A';
                    int y2 = response.charAt(1) - '0';

                    if (!layout.locationExists(x2, y2)) {
                        System.out.println("Emplacement invalide.\n");
                    }
                    else {
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
                                System.out.println("La carte que tu deplaces doit etre adjacente a une autre.\n");
                                error = true;
                            }

                            // Replace the card
                            layout.setCardAt(x1, y1, originCard);
                        }

                        if (!error) {
                            if(layout.moveCard(x1, y1, x2, y2)) {
                                // Card has been moved, we are done
                                return;
                            }
                            else {
                                System.out.println("Une erreur est survenue...\n");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Plays a turn
     */
	public void play() {
		
		isTurnOver = false;

        // Draw a card
        drawnCard = Stack.getInstance().drawCard();

        // Display layout
        layout.display();

        // Display drawn card
        System.out.println("Tu viens de piocher : " + drawnCard);

        // Display victory card
        System.out.println("Ta Victory Card est : " + this.victoryCard + "\n");
        
        //OPTIONS
    	availableOptions.add(Actions.PlaceCard);
    	availableOptions.add(Actions.SeeVictoryCard);
    	availableOptions.add(Actions.MoveLayout);

        // Ask what action to do if the layout has 2 cards at least
        int answer = 1;
        if (layout.countCards() > 1) {
        	availableOptions.add(Actions.MoveCards);
        	
        	
        	
            answer = Input.promptChoice(
                "Tour de jeu",
                new String[] { "Poser ma carte", "Deplacer une carte","Bouger le plateau" },
                "Que veux-tu faire ?"
            );
        }	

        switch (answer) {
            case 1:
                // Place drawn card
                this.placeCard(drawnCard);

                if (layout.countCards() > 1) {
                    // Display layout
                    layout.display();

                    int answer2 = Input.promptChoice(
                        "Deplacer une carte",
                        new String[] { "Non", "Oui" },
                        "Veux-tu deplacer une carte ?",
                        0
                    );

                    if(answer2 == 1) {
                        // Move card
                        this.moveCard();
                    }
                }
                break;

            case 2:
                // Move card
                this.moveCard();

                // Display layout
                layout.display();

                // Place drawn card
                System.out.println("Tu dois maintenant poser ta carte !");
                this.placeCard(drawnCard);
                break;

            //TEST    
            case 3:
            	int answer2 = 0;
            	do {
	            	 answer2 = Input.promptChoice(
	                        "Deplacer la grille",
	                        new String[] { "Haut", "Bas","Gauche","Droite","Quitter" },
	                        "Quelle direction ?"
	                    );
	            	
	            	boolean result;
	            	
	            	switch(answer2)
	            	{
	            		case 1: 
	            			result = layout.moveVertically(-1);
	            			break;
	            		case 2: 
	            			result = layout.moveVertically(1);
	            			break;
	            		case 3: 
	            			result = layout.moveHorizontally(-1);
	            			break;
	            		case 4: 
	            			result = layout.moveHorizontally(1);
	            			break;
	            		default:
	            			result = false;
	            			break;
	            	}
	            	
	            	if(!result)
	            		System.out.println("Impossible de déplacer le Layout ici");
	            	else
	            		layout.display();
            	
            	}while(answer2 != 5);
            	
            	break;
                
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
