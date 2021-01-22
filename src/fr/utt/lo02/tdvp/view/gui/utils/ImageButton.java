package fr.utt.lo02.tdvp.view.gui.utils;

import javafx.scene.control.Button;

/**
 * Display a button with an image as a background.
 * @see Button
 */
public class ImageButton extends Button {
    /**
     * Instantiates a new ImageButton with the specified image as a background.
     * @param imageURI The URI of the image to display as background, relative to "/resources/" directory
     */
    public ImageButton(String imageURI) {
        this.changeImage(imageURI);
        this.getStyleClass().add("no-style");
    }

    /**
     * Change the image to use as a background for this button.
     * @param imageURI The URI of the new image, relative to "/resources/" directory
     */
    public void changeImage(String imageURI) {
        this.setGraphic(ImageUtil.getImageView(imageURI));
    }
}
