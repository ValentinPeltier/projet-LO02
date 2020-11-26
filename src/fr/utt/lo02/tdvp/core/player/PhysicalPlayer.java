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

    private void placeCard(Card card) {
        Layout layout = GameManager.getInstance().getLayout();

        while(true) {
            String response;
            do {
                response = Input.promptString("Où veux-tu poser ta carte " + card + " ? (e.g. \"B2\")");

                if (response.length() != 2) {
                    System.out.println("Emplacement invalide.\n");
                }
            } while(response.length() != 2);
            int x = response.charAt(0) - 'A';
            int y = response.charAt(1) - '0';

            if (!layout.locationExists(x, y)) {
                System.out.println("Emplacement invalide.\n");
            }
            else if (layout.getCardAt(x, y) != null) {
                System.out.println("Il y a déjà une carte ici !\n");
            }
            else if (
                !layout.isEmpty()
                && layout.getCardAt(x - 1, y) == null
                && layout.getCardAt(x + 1, y) == null
                && layout.getCardAt(x, y - 1) == null
                && layout.getCardAt(x, y + 1) == null
            ) {
                System.out.println("Il faut que ta carte soit adjacente à une autre !\n");
            }
            else if (layout.placeCard(x, y, card)) {
                // Card has been placed, we are done
                return;
            }
            else {
                System.out.println("Une erreur est survenue...\n");
            }
        }
    }

    private void moveCard() {
        Layout layout = GameManager.getInstance().getLayout();

        // Ask for the card to move
        while (true) {
            String response;
            do {
                response = Input.promptString("Quelle carte veux-tu déplacer ? (e.g. \"B2\")");

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
                    response = Input.promptString("Où veux-tu déplacer cette carte ? (e.g. \"B2\")");
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
                                System.out.println("La carte que tu déplaces doit être adjacente à une autre.\n");
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
        Layout layout = GameManager.getInstance().getLayout();

        // Draw a card
        Card drawnCard = Stack.getInstance().drawCard();

        // Display layout
        layout.display();

        // Display name
        System.out.println("### A " + this.name + " de jouer ! ###\n");

        // Display drawn card
        System.out.println("Tu viens de piocher : " + drawnCard);

        // Display victory card
        System.out.println("Ta Victory Card est : " + this.victoryCard + "\n");

        // Ask what action to do if the layout has 2 cards at least
        int answer = 1;
        if (layout.countCards() > 1) {
            answer = Input.promptChoice(
                "Tour de jeu",
                new String[] { "Poser ma carte", "Deplacer une carte" },
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
        }
	}

    /**
     * Prompt the name of the player
     */
    protected void initName() {
        // Ask to type in the player's name
        this.name = Input.promptString("Choisis un nom pour le joueur " + (++initializedCount) + " :");
    }
}
