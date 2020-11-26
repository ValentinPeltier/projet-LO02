package fr.utt.lo02.tdvp.core.player;

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
	public void play() {
        GameManager gameManager = GameManager.getInstance();
		Card drawnCard = Stack.getInstance().drawCard();

		gameManager.getLayout().display();

		System.out.println("C'est a " + name + " de jouer ! \nTu viens de Piocher : "+drawnCard+"\n");

		// Display Victory Card Choice
        int answer = Input.promptChoice(
            "Voir ta Victory Card",
            new String[] { "Non", "Oui" },
            "Veux-tu voir ta Victory Card ?",
            0
        );

        if(answer == 1) {
        	this.displayVictoryCard();
        }

		// Ask first Time
        answer = Input.promptChoice(
            "Options",
            new String[] { "Poser ma carte", "Deplacer des cartes" },
            "Que voulez vous faire ?"
        );

        switch(answer) {
        	case 1:
                // TODO : poser cartes

        		int answer2 = Input.promptChoice(
                        "Deplacer une carte",
                        new String[] { "Non", "Oui" },
                        "Voulez-vous deplacer une ou plusieurs carte.s ?",
                        0
                    );

        		if(answer2 == 1) {
        			// TODO: deplacer cartes
                }

        		break;
        	case 2:
                // TODO : deplacer cartes

                System.out.println("Vous devez maintenant poser votre carte !");

        		// TODO : poser carte
        		break;
        }
	}

    /**
     * Display the victory card
     */
    public void displayVictoryCard() {
        System.out.println("Ta Victory Card est " + this.victoryCard + "\n");
    }

    /**
     * Prompt the name of the player
     */
    protected void initName() {
        // Ask to type in the player's name
        this.name = Input.promptString("Choisissez un nom pour le joueur " + (++initializedCount) + " :");
    }
}
