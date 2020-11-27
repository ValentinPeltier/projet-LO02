package fr.utt.lo02.tdvp.core.variant;

import fr.utt.lo02.tdvp.core.GameManager;
import fr.utt.lo02.tdvp.core.Stack;
import fr.utt.lo02.tdvp.core.cli.Input;
import fr.utt.lo02.tdvp.core.layout.Layout;
import fr.utt.lo02.tdvp.core.player.Player;

public class VariantSecondChance extends Variant {
    static final String name = "Seconde chance";
    static final String description = "donne la possibilite a tous les joueurs de re-piocher une Victory Card en milieu de round";

    private int executeAtTurn;

    public static String getName() {
        return name;
    }

    public static String getDescription() {
        return description;
    }

    /**
     * Should the variant be executed ?
     * @param turn The index of the turn currently playing
     * @param playerIndex The index of the player within the turn
     * @return {@code true} if the variant should be executed now, {@code false} otherwise
     */
    public boolean shouldExecute(int turn, int playerIndex) {
        // Should execute now ?
        if (turn == executeAtTurn) {
            return true;
        }
        // If variant has never been executed
        else if (executeAtTurn == -1) {
            Layout layout = GameManager.getInstance().getLayout();

            // Execute when we are in the middle of the round, so the layout is half filled
            boolean secondHalfRound = layout.countCards() >= Math.floor(layout.getLocations().size() / 2);

            if (secondHalfRound) {
                // If we are at the beginning of the turn
                if (playerIndex == 0) {
                    // We can execute it this turn
                    executeAtTurn = turn;
                    return true;
                }

                // Otherwise, we will execute it next turn
                executeAtTurn = turn + 1;
            }
        }

        return false;
    }

    public void reset() {
        this.executeAtTurn = -1;
    }

    /**
     * Executes the variant
     */
    public void execute(Player player) {
        GameManager.getInstance().getLayout().display();

        final int drawNewVictoryCard = Input.promptChoice(
            "[Variante] Repiocher une Victory Card",
            new String[] { "Non", "Oui" },
            "Souhaites-tu repiocher une Victory Card et remettre la tienne (" + player.getVictoryCard() + ") dans la pioche ?",
            0
        );

        if (drawNewVictoryCard == 1) {
            // Put the victory card back in the stack
            Stack.getInstance().put(player.getVictoryCard());

            // Draw a new victory card
            player.drawVictoryCard();

            System.out.println("Tu as pioché ta nouvelle Victory Card : " + player.getVictoryCard() + "\n");
        }
    }
}
