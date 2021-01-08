package fr.utt.lo02.tdvp.model.variant;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.model.Stack;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.player.Player;
import fr.utt.lo02.tdvp.view.cli.Input;

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
            Layout layout = Settings.getInstance().getLayout();

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
        Settings.getInstance().getLayout().display();

        //ASK VIEW
        GameManager.getInstance().notifyObservers(Events.AskVariantSecondChance);
    }

    public void makeChange()
    {
    	Player player = GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex());
    	// Put the victory card back in the stack
        Stack.getInstance().put(player.getVictoryCard());

        // Draw a new victory card
        player.drawVictoryCard();

        GameManager.getInstance().notifyObservers(Events.DisplayVariantSeconChance);
    }
}
