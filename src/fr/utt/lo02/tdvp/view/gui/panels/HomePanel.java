package fr.utt.lo02.tdvp.view.gui.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;

import fr.utt.lo02.tdvp.view.gui.components.Panel;
import fr.utt.lo02.tdvp.view.gui.Label;
import fr.utt.lo02.tdvp.view.gui.Button;
import fr.utt.lo02.tdvp.view.gui.Window;

public class HomePanel extends Panel {
    public HomePanel() {
        super();

        Label title = new Label("Shape Up !");
        title.setFont(title.getFont().deriveFont(60f));
        title.setBorder(new EmptyBorder(50, 0, 0, 0));

        Panel boardPanel = new Panel();
        boardPanel.setLayout(new GridLayout(3, 5));
        boardPanel.setBorder(new EmptyBorder(60, 0, 60, 0));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/TRH.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/CBH.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/SGF.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));
        boardPanel.add(new JLabel(new ImageIcon("assets/cards/empty.png")));

        Button playButton = new Button("Jouer") {
            public void handleClick(MouseEvent event) {
                Window.getInstance().setActivePanel(Window.getInstance().getSettingsPanel());
            }
        };

        this.setLayout(new BorderLayout(100, 50));
        this.add(title, BorderLayout.PAGE_START);
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(playButton, BorderLayout.PAGE_END);
    }
}
