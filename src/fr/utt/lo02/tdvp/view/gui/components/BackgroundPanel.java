package fr.utt.lo02.tdvp.view.gui.components;

import java.awt.*;

public class BackgroundPanel extends Panel {
    private Image image;

    public BackgroundPanel(Image image) {
        super();

        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, this);
    }
}
