package fr.utt.lo02.tdvp.core.variant;

import fr.utt.lo02.tdvp.core.player.Player;

public abstract class Variant {
    public Variant() {
        this.reset();
    }

    public abstract boolean shouldExecute(int turn, int playerIndex);
    public abstract void execute(Player player);
    public abstract void reset();
}
