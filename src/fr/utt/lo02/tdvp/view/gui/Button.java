package fr.utt.lo02.tdvp.view.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public abstract class Button extends JPanel {
    private boolean inside = false;
    private boolean pressed = false;
    // Gradient colors
    private Color backgroundColor = new Color(152, 210, 255, 42);

    public Button(String text) {
        super();

        // Add listeners
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                handleClick(event);
            }

            // @Override
            // public void mousePressed(MouseEvent event) {
            //     pressed = true;
            //     repaint();
            // }

            // @Override
            // public void mouseReleased(MouseEvent event) {
            //     pressed = false;
            //     repaint();
            // }

            // @Override
            // public void mouseEntered(MouseEvent event) {
            //     inside = true;
            //     repaint();
            // }

            // @Override
            // public void mouseExited(MouseEvent event) {
            //     inside = false;
            //     repaint();
            // }
        });

        // Add the text
        JLabel label = new Label(text);
        label.setForeground(new Color(255, 255, 255));
        label.setBorder(new EmptyBorder(10, 20, 10, 20));

        this.setBorder(BorderFactory.createLineBorder(new Color(152, 210, 255), 5, true));
        this.setBackground(this.backgroundColor);
        this.setOpaque(true);

        this.setLayout(new GridBagLayout());
        this.add(label, new GridBagConstraints());
    }

    public abstract void handleClick(MouseEvent event);
}
