package fr.utt.lo02.tdvp.model.panel;

import java.util.Observable;

import fr.utt.lo02.tdvp.controller.GamePanelController;
import fr.utt.lo02.tdvp.view.gui.GamePanelView;

public class GamePanel extends Observable {
    private static GamePanel instance = new GamePanel();

    public static GamePanel getInstance() {
        return instance;
    }

    private GamePanel() {
        GamePanelController.getInstance().init();
        addObserver(GamePanelView.getInstance());
    }

    public void init() {
        // TODO
    }
}
