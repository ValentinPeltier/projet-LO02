package fr.utt.lo02.tdvp.view.gui;

import javafx.scene.control.Label;

public class GameLabel extends Label {
    public GameLabel(String text) {
        super(text);

        this.getStylesheets().add("/styles/label.css");
        this.getStyleClass().add("label");
    }
}
