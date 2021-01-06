package fr.utt.lo02.tdvp.view.gui;

import javafx.scene.control.Button;

public class SUButton extends Button {
    public SUButton(String text) {
        super(text);

        this.getStylesheets().add("/styles/button.css");
        this.getStyleClass().add("button");
    }
}
