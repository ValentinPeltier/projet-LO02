package fr.utt.lo02.tdvp.core.variant;

public abstract class Variant {
    public abstract boolean shouldExecute(int turn, int playerIndex);
    public abstract void execute();
}
