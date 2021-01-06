package fr.utt.lo02.tdvp.view.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Label extends JLabel {
    public Label(String text) {
        super(text, SwingConstants.CENTER);

        this.setForeground(new Color(255, 255, 255));

        try {
            // Use custom font : https://stackoverflow.com/a/21081640
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Retron2000.ttf"));
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
            font = font.deriveFont(20f);

            this.setFont(font);
        }
        catch(IOException e) {
            System.out.println("[Exception] Cannot use custom font : " + e.getMessage());
        }
        catch(FontFormatException e) {
            System.out.println("[Exception] Cannot use custom font : " + e.getMessage());
        }
    }
}
