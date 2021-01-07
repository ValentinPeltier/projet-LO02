package fr.utt.lo02.tdvp.view.gui;

import javafx.scene.layout.Pane;

public class GamePanel extends Pane {
    private static GamePanel instance = new GamePanel();

    public static GamePanel getInstance() {
        return instance;
    }

    private GamePanel() {
        // TODO
    }
}
