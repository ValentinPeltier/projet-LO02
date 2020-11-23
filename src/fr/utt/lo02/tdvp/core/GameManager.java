package fr.utt.lo02.tdvp.core;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.tdvp.core.player.Player;
public class GameManager {
    private static GameManager instance = new GameManager();

    private List<Player> players = new ArrayList<Player>();

    private GameManager() {}

    /**
     * Get the single instance of the class
     * @return the single instance of GameManager
     */
    public static GameManager getInstance() {
        return instance;
    }

    public void initializeGame() {
        // TODO
    }

    public void playGame() {
        // TODO
    }
}
