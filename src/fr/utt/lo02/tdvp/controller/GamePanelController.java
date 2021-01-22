package fr.utt.lo02.tdvp.controller;

public class GamePanelController {
    private static GamePanelController instance = new GamePanelController();

    public static GamePanelController getInstance() {
        return instance;
    }

    private GamePanelController() {}

    public void init() {

    }
}
