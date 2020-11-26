package fr.utt.lo02.tdvp.core.variant;

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
     * @return {@code true} if the variant should be executed now, {@code false} otherwise
     */
    public boolean shouldExecute() {
        // TODO
        return true;
    }

    /**
     * Executes the variant
     */
    public void execute() {
        // TODO
        System.out.println("VariantRandomSwitch.execute()");
    }
}
