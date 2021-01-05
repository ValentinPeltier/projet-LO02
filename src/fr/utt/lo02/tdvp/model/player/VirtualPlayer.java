package fr.utt.lo02.tdvp.model.player;

public abstract class VirtualPlayer extends Player {
    /**
     * Count the number of initialized virtual players
     */
    private static int initializedCount = 0;

    /**
     * Generate a name for this player
     */
    protected void initName() {
        this.name = "Joueur virtuel " + String.valueOf(++initializedCount);
    }
}
