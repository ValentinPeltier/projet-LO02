package fr.utt.lo02.tdvp.view.gui.panels;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fr.utt.lo02.tdvp.view.gui.Button;
import fr.utt.lo02.tdvp.view.gui.Label;
import fr.utt.lo02.tdvp.view.gui.Window;
import fr.utt.lo02.tdvp.view.gui.components.BackgroundPanel;
import fr.utt.lo02.tdvp.view.gui.components.Panel;

public class SettingsPanel extends Panel {
    private enum Step {
        PHYSICAL_PLAYERS,
        VIRTUAL_PLAYERS,
        LAYOUT,
        VARIANT,
        PHYSICAL_PLAYERS_NAME_1,
        PHYSICAL_PLAYERS_NAME_2,
        PHYSICAL_PLAYERS_NAME_3
    };

    private Image panelBackgroundImage;
    private Panel panel;

    private Step step;

    private int physicalPlayersCount;

    public SettingsPanel() {
        super();

        try {
            this.panelBackgroundImage = ImageIO.read(new File("assets/panels/settings.png"));
        }
        catch(Exception e) {
            System.out.println("[Exception] Cannot read assets/panels/settings.png : " + e.getMessage());
        }

        Label title = new Label("Shape Up !");
        title.setFont(title.getFont().deriveFont(60f));
        title.setBorder(new EmptyBorder(50, 0, 0, 0));

        this.panel = new BackgroundPanel(this.panelBackgroundImage);
        this.panel.setPreferredSize(new Dimension(1000, 376));
        this.panel.setLayout(new BorderLayout(0, 40));
        this.panel.setBorder(new EmptyBorder(50, 0, 0, 0));

        this.setStep(Step.PHYSICAL_PLAYERS);

        Panel buttonsPanel = new Panel();
        buttonsPanel.add(new Button("RETOUR") {
            @Override
            public void handleClick(MouseEvent event) {
                switch(step) {
                    case PHYSICAL_PLAYERS:
                        // Go back to the home screen
                        Window.getInstance().setActivePanel(Window.getInstance().getHomePanel());
                        break;
                    case VIRTUAL_PLAYERS:
                        setStep(Step.PHYSICAL_PLAYERS);
                        break;
                    case LAYOUT:
                        setStep(Step.VIRTUAL_PLAYERS);
                        break;
                    case VARIANT:
                        setStep(Step.LAYOUT);
                        break;
                    case PHYSICAL_PLAYERS_NAME_1:
                        setStep(Step.VARIANT);
                        break;
                    case PHYSICAL_PLAYERS_NAME_2:
                        setStep(Step.PHYSICAL_PLAYERS_NAME_1);
                        break;
                    case PHYSICAL_PLAYERS_NAME_3:
                        setStep(Step.PHYSICAL_PLAYERS_NAME_2);
                        break;
                }
            }
        });
        buttonsPanel.add(new Button("SUIVANT") {
            @Override
            public void handleClick(MouseEvent event) {
                switch(step) {
                    case PHYSICAL_PLAYERS:
                        setStep(Step.VIRTUAL_PLAYERS);
                        break;
                    case VIRTUAL_PLAYERS:
                        setStep(Step.LAYOUT);
                        break;
                    case LAYOUT:
                        setStep(Step.VARIANT);
                        break;
                    case VARIANT:
                        setStep(Step.PHYSICAL_PLAYERS_NAME_1);
                        break;
                    case PHYSICAL_PLAYERS_NAME_1:
                        if (physicalPlayersCount == 1) {
                            Window.getInstance().setActivePanel(Window.getInstance().getGamePanel());
                        }
                        else {
                            setStep(Step.PHYSICAL_PLAYERS_NAME_2);
                        }
                        break;
                    case PHYSICAL_PLAYERS_NAME_2:
                        if (physicalPlayersCount == 2) {
                            Window.getInstance().setActivePanel(Window.getInstance().getGamePanel());
                        }
                        else {
                            setStep(Step.PHYSICAL_PLAYERS_NAME_3);
                        }
                        break;
                    case PHYSICAL_PLAYERS_NAME_3:
                        Window.getInstance().setActivePanel(Window.getInstance().getGamePanel());
                        break;
                }
            }
        });

        this.setLayout(new BorderLayout(0, 50));
        this.add(title, BorderLayout.PAGE_START);
        this.add(this.panel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.PAGE_END);
    }

    private void setStep(Step step) {
        System.out.println(step);
        this.step = step;

        Label label = new Label("");
        Panel currentPanel = new Panel();

        switch(step) {
            case PHYSICAL_PLAYERS:
                label.setText("Nombre de joueurs réels");
                currentPanel.add(new JLabel(new ImageIcon("assets/icons/user-selected.png")));
                currentPanel.add(new JLabel(new ImageIcon("assets/icons/user.png")));
                currentPanel.add(new JLabel(new ImageIcon("assets/icons/user.png")));
                break;
            case VIRTUAL_PLAYERS:
                label.setText("Nombre de joueurs virtuels");
                currentPanel.add(new JLabel(new ImageIcon("assets/icons/user.png")));
                currentPanel.add(new JLabel(new ImageIcon("assets/icons/user.png")));
                break;
        }

        this.panel.add(label, BorderLayout.PAGE_START);
        this.panel.add(currentPanel, BorderLayout.CENTER);
        this.panel.revalidate();
    }
}
