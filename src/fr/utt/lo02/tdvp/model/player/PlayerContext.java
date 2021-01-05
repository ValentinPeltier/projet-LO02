package fr.utt.lo02.tdvp.core.player;

public class PlayerContext {
    private Player strategy;

    /**
     * Assign a strategy to this context
     * @param strategy the strategy to apply
     */
    public PlayerContext(Player strategy) {
        this.strategy = strategy;
    }

    /**
     * Play a turn
     */
    public void play() {
        this.strategy.play();
    }

    /**
     * Returns the name of the player
     * @return the name of the player
     */
    public String getName() {
        return this.strategy.getName();
    }

    /**
     * Set the score of the player
     * @param score the new score of the player
     */
    public void setScore(int score) {
        this.strategy.setScore(score);
    }

    /**
     * Returns the score of the player
     * @return the score of the player
     */
    public int getScore() {
        return this.strategy.getScore();
    }
}
