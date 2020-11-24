package fr.utt.lo02.tdvp.core.player;

import java.util.ArrayList;
import java.util.List;
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
    Scanner sc = new Scanner(System.in);
	GameManager gameManager = GameManager.getInstance();
	Stack stack = Stack.getInstance();
	
	//TEST
	private List<String> choices = new ArrayList<String>();
	
	public void play() {
		
		Card drawnCard;
		
		int answer;
		boolean isTurnFinished = false;
		
		
		
		drawnCard = stack.drawCard();
		
		//Liste d'action dans une liste que tu remove
		choices.add("Voir ma victory Card");
		choices.add("Poser la carte");
		choices.add("Déplacer des cartes");
		choices.add("Voir la carte piochee");
		
		
		while(!isTurnFinished)
		{			
			System.out.println("C'est à "+name+" de jouer ! Tu as pioché une carte !");
	    	
			for(int i=0; i < choices.size(); i++)
			{
				System.out.println(i+1+" - "+choices.get(i));
			}
			
			System.out.println("0 - Finir Le Tour");
	    	
	    	answer = sc.nextInt();
	    	
		   //IF MACHIN
		}  	
    	
    	
    	
    }

    /**
     * Display the victory card
     */
    public void displayVictoryCard() {
        System.out.println("Votre carte est " + this.victoryCard);
    }

    /**
     * Prompt the name of the player
     */
    protected void initName() {
        // Ask to type in the player's name
        this.name = Input.promptString("Choisissez un nom pour le joueur " + (++initializedCount) + " :");
    }
}
