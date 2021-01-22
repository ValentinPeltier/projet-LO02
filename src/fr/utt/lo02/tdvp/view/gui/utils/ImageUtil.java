package fr.utt.lo02.tdvp.view.gui.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtil {
    /**
     *
     * @param uri The image URI, without the "/resources/" prefix
     * @return The requested image or null if the image is unfoundable
     */
    public static Image getImage(String uri) {
        return new Image(ImageUtil.class.getResource("/resources/" + uri).toString());
    }

    /**
     * @param uri The image URI, without the "/resources/" prefix
     * @return The requested image in an ImageView or an empty ImageView if the image is unfoundable
     */
    public static ImageView getImageView(String uri) {
        return new ImageView(getImage(uri));
    }
}
