package fr.utt.lo02.tdvp.view.gui.utils;

import javafx.scene.control.Button;

public class ImageButton extends Button {
    public ImageButton(String imageURI) {
        this.init(imageURI);
    }

    private void init(String imageURI) {
        this.changeImage(imageURI);
        this.getStyleClass().add("no-style");
    }

    public void changeImage(String imageURI) {
        this.setGraphic(ImageUtil.getImageView(imageURI));
    }
}
