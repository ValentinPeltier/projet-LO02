package fr.utt.lo02.tdvp.core.player;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import fr.utt.lo02.tdvp.core.Card;
import fr.utt.lo02.tdvp.core.GameManager;
import fr.utt.lo02.tdvp.core.Stack;
import fr.utt.lo02.tdvp.core.cli.Input;

public class PhysicalPlayer extends Player {
    /**
     * Count the number of initialized physical players
     */
    private static int initializedCount = 0;

    /**
     * Plays a turn
     */
	GameManager gameManager = GameManager.getInstance();
	Stack stack = Stack.getInstance();
	
	public void play() {
		
		Card drawnCard;
		
		int answer;
		
		drawnCard = stack.drawCard();
		
		gameManager.getLayout().display();
		
		System.out.println("\nC'est a "+name+" de jouer ! \nTu viens de Piocher : "+drawnCard+"\n");
		
		// Victory Card Choice
        answer = Input.promptChoice(
            "Victory Card",
            new String[] { "Oui", "Non" },
            "Veux-tu voir ta Victory Card ?"
        );
        
        if(answer == 1)
        {
        	displayVictoryCard();
        }
        
        if (gameManager.getVariant().getClass().getSimpleName() == "VariantSecondChance")
        {
        	gameManager.getVariant().execute();
        }
        
		
		// Ask first Time
        answer = Input.promptChoice(
            "Options",
            new String[] { "Poser ma carte", "Deplacer des cartes" },
            "Que voulez vous faire ?"
        );
        
        switch(answer) {
        	case 1:
        		//TODO : poser cartes
        		int answer2 = Input.promptChoice(
                        "Deplacer une carte",
                        new String[] {"Oui","Non"},
                        "Voulez voux deplacer une ou des carte.s ?"
                    );
        		if(answer2 == 0)
        		{
        			//TODO: deplacer cartes
        		}
        		break;
        	case 2:
        		//TODO : deplacer cartes
        		System.out.println("Vous devez maintenant poser votre carte !");
        		//TODO : poser carte
        		break;
        }
	}

    /**
     * Display the victory card
     */
    public void displayVictoryCard() {
        System.out.println("Votre carte Victoire est " + this.victoryCard);
    }

    /**
     * Prompt the name of the player
     */
    protected void initName() {
        // Ask to type in the player's name
        this.name = Input.promptString("Choisissez un nom pour le joueur " + (++initializedCount) + " :");
    }
}
