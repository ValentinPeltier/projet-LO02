package fr.utt.lo02.tdvp.controller;

import fr.utt.lo02.tdvp.view.gui.GamePanelView;

public class GamePanelController {
    private static GamePanelController instance = new GamePanelController();

    public static GamePanelController getInstance() {
        return instance;
    }

    private GamePanelController() {}

    public void init() {
        GamePanelView view = GamePanelView.getInstance();

        // TODO
    }
}
