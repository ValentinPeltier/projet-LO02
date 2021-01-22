package fr.utt.lo02.tdvp.model.variant;

import fr.utt.lo02.tdvp.model.player.Player;

public abstract class Variant {
    public static enum Name {
        RandomSwitch,
        SecondChance,
    };

    public Variant() {
        this.reset();
    }

    public abstract boolean shouldExecute(int turn, int playerIndex);
    public abstract void execute(Player player);
    public abstract void reset();
    public abstract void makeChange();
}
