package fr.utt.lo02.tdvp.view.gui.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is an util for creating Image and ImageView objects from an URL.
 * @see Image
 * @see ImageView
 */
public class ImageUtil {
    /**
     * Returns an Image instance, displaying the specified image.
     * @param uri The image URI, relative to the /resources/ directory
     * @return The requested image or null if the image is unfoundable
     * @see Image
     */
    public static Image getImage(String uri) {
        return new Image(ImageUtil.class.getResource("/resources/" + uri).toString());
    }

    /**
     * Returns an ImageView instance, displaying the specified image.
     * @param uri The image URI, relative to the /resources/ directory
     * @return The requested image in an ImageView or an empty ImageView if the image is unfoundable
     * @see ImageView
     */
    public static ImageView getImageView(String uri) {
        return new ImageView(getImage(uri));
    }
}
