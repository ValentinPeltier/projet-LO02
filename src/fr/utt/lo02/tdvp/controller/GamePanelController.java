package fr.utt.lo02.tdvp.controller;

/**
 * Represents the controller of the game panel.
 * This class uses the singleton design pattern.
 */
public class GamePanelController {
    /**
     * The unique instance of the class.
     * @see #getInstance()
     */
    private static GamePanelController instance = new GamePanelController();

    /**
     * Returns the unique instance of the class.
     * @return The unique instance of the class
     */
    public static GamePanelController getInstance() {
        return instance;
    }

    /**
     * Instantiates a new GamePanelController object.
     * Does nothing.
     */
    private GamePanelController() {}
}
