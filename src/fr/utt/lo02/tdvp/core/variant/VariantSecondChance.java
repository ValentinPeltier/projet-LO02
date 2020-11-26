package fr.utt.lo02.tdvp.core.variant;

public class VariantSecondChance extends Variant {
    static final String name = "Seconde chance";
    static final String description = "donne la possibilite a tous les joueurs de re-piocher une Victory Card en milieu de partie";

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
        // TODO
        return true;
    }

    /**
     * Executes the variant
     */
    public void execute() {
        // TODO
        System.out.println("VariantSecondChance.execute()");
    }
}
