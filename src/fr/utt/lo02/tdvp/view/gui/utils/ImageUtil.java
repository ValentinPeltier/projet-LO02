package fr.utt.lo02.tdvp.view.gui.utils;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtil {
    /**
     *
     * @param uri The image URI, without the "src/assets/" prefix
     * @return The requested image or null if the image is unfoundable
     */
    public static Image getImage(String uri) {
        try {
            return new Image(new FileInputStream("src/assets/" + uri));
        }
        catch(IOException e) {
            System.out.println("Cannot read image src/assets/" + uri + " : " + e.getMessage());
        }

        return null;
    }

    /**
     * @param uri The image URI, without the "src/assets/" prefix
     * @return The requested image in an ImageView or an empty ImageView if the image is unfoundable
     */
    public static ImageView getImageView(String uri) {
        return new ImageView(getImage(uri));
    }
}
