package fr.utt.lo02.tdvp.view.gui;

import javafx.scene.control.Button;

public class ImageButton extends Button {
    public ImageButton(String imageURI) {
        super();
        this.init(imageURI);
    }

    private void init(String imageURI) {
        this.changeImage(imageURI);
        this.getStyleClass().add("no-style");
    }

    public void changeImage(String imageURI) {
        this.setGraphic(Images.getImageView(imageURI));
    }
}
