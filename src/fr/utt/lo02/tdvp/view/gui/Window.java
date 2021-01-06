package fr.utt.lo02.tdvp.view.gui;

import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import fr.utt.lo02.tdvp.view.gui.components.BackgroundPanel;
import fr.utt.lo02.tdvp.view.gui.panels.*;

public class Window extends JComponent {
    private static Window instance = new Window();

    private Image backgroundImage;

    private JFrame frame;
    private JPanel homePanel = new HomePanel();
    private JPanel settingsPanel = new SettingsPanel();
    private JPanel gamePanel = new GamePanel();

    private Window() {
        // Create the window
        this.frame = new JFrame("Shape Up !");

        try {
            // Set the window icon
            this.frame.setIconImage(ImageIO.read(new File("assets/icon.png")));

            // Load the background
            this.backgroundImage = ImageIO.read(new File("assets/background.png"));
        }
        catch (IOException e) {
            System.out.println("Cannot read assets/title.png image");
        }

        // Add background panel
        this.frame.setContentPane(new BackgroundPanel(this.backgroundImage));

        // Start with the home panel
        this.frame.getContentPane().add(homePanel);

        // Set the close action
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the window
        this.frame.setVisible(true);

        // Set window dimension to fullscreen
        this.frame.setMinimumSize(new Dimension(500, 300));
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static Window getInstance() {
        return instance;
    }

    public JPanel getHomePanel() {
        return this.homePanel;
    }

    public JPanel getSettingsPanel() {
        return this.settingsPanel;
    }

    public JPanel getGamePanel() {
        return this.gamePanel;
    }

    public void setActivePanel(JPanel panel) {
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(panel);
        this.frame.revalidate();
        this.frame.repaint();
    }
}
