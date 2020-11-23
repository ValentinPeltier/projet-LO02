package fr.utt.lo02.tdvp.core.player;

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
        // TODO
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
        System.out.print("Choisissez un nom pour le joueur " + (++initializedCount) + " : ");

        this.name = Input.getScanner().nextLine();
    }
}
