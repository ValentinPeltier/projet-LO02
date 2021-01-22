package fr.utt.lo02.tdvp.model.variant;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.layout.Location;
import fr.utt.lo02.tdvp.model.player.Player;

public class VariantRandomSwitch extends Variant {
    static final String name = "Echange aleatoire";
    static final String description = "echange aleatoirement deux cartes sur le plateau a chaque tour de jeu";

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
        // Should not execute in first turn and only once per turn
        return turn > 0 && playerIndex == 0;
    }

    /**
     * Executes the variant
     */
    public void execute(Player player) {
        Layout layout = Settings.getInstance().getLayout();

        // Get 2 random distinct locations
        Location location1 = layout.getRandomLocation();
        Location location2;
        do {
            location2 = layout.getRandomLocation();
        } while(location1.toString().equals(location2.toString()));

        // Swap these cards
        Card cardTmp = layout.getCardAt(location1);
        layout.setCardAt(location1, layout.getCardAt(location2));
        layout.setCardAt(location2, cardTmp);

        //Display Message
        GameManager.getInstance().notifyObservers(Events.DisplayVariantRandomSwitch);
    }

    public void reset() {}

	@Override
	public void makeChange() {
	}
}
