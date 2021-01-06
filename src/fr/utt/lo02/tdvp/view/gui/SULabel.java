package fr.utt.lo02.tdvp.view.gui;

import javafx.scene.control.Label;

public class SULabel extends Label {
    public SULabel(String text) {
        super(text);

        this.getStylesheets().add("/styles/label.css");
        this.getStyleClass().add("label");
    }
}
