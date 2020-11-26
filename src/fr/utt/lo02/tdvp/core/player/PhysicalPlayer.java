package fr.utt.lo02.tdvp.core.player;

import fr.utt.lo02.tdvp.core.Card;
import fr.utt.lo02.tdvp.core.GameManager;
import fr.utt.lo02.tdvp.core.Stack;
import fr.utt.lo02.tdvp.core.cli.Input;
import fr.utt.lo02.tdvp.core.layout.Layout;

public class PhysicalPlayer extends Player {
    /**
     * Count the number of initialized physical players
     */
    private static int initializedCount = 0;

    /**
     * Plays a turn
     */
	public void play() {
        Layout layout = GameManager.getInstance().getLayout();

        // Draw a card
        Card drawnCard = Stack.getInstance().drawCard();

        // Display player name
        System.out.println("### C'est a " + name + " de jouer ! ###\n");

        // Display layout
        layout.display();

        // Display drawn card
        System.out.println("Tu viens de piocher : " + drawnCard + "\n");

		// Ask if the user want to display the victory card
        int answer = Input.promptChoice(
            "Voir ta Victory Card",
            new String[] { "Non", "Oui" },
            "Veux-tu voir ta Victory Card ?",
            0
        );

        // Display victory card
        if(answer == 1) {
            this.displayVictoryCard();

            //? layout.display();
        }

        // Ask what action to do if the layout has 2 cards at least
        if (layout.countCards() > 1) {
            answer = Input.promptChoice(
                "Options",
                new String[] { "Poser ma carte", "Deplacer une carte" },
                "Que veux-tu faire ?"
            );
        }
        else {
            // Otherwise, the player can only place his/her card
            answer = 1;
        }

        switch(answer) {
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
