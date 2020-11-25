package fr.utt.lo02.tdvp.core;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.tdvp.core.cli.Input;
import fr.utt.lo02.tdvp.core.player.Player;
import fr.utt.lo02.tdvp.core.variant.Variant;
import fr.utt.lo02.tdvp.core.variant.VariantRandomSwitch;
import fr.utt.lo02.tdvp.core.variant.VariantSecondChance;

public class GameManager {
    private static GameManager instance = new GameManager();

    private List<Player> players = new ArrayList<Player>();

    private Variant variant;

    private GameManager() {}

    /**
     * Get the single instance of the class
     * @return the single instance of GameManager
     */
    public static GameManager getInstance() {
        return instance;
    }

    /**
     * Initialize a new game.
     * Choose a variant, a number of players, their names and how many virtual players there should be.
     */
    public void initializeGame() {
        // Initialize the variant
        initializeVariant();
    }

    /**
     * Asks the user to choose a variant
     */
    private void initializeVariant() {
        // Ask the user for a variant
        int variantChoice = Input.promptChoice("Choix de la variante", new String[]{
            "Aucune",
            VariantRandomSwitch.getName() + " (" + VariantRandomSwitch.getDescription() + ")",
            VariantSecondChance.getName() + " (" + VariantSecondChance.getDescription() + ")"
        }, "Quelle variante voulez-vous choisir ?", 0);

        // Set the variant
        switch(variantChoice) {
            case 0:
                // Create a variant that does nothing
                this.variant = new Variant() {
                    public void execute() {}
                };
                break;
            case 1:
                // RandomSwitch variant
                this.variant = new VariantRandomSwitch();
                break;
            case 2:
                this.variant = new VariantSecondChance();
                break;
        }
    }

    public void playGame() {
        // TODO
    }
}
