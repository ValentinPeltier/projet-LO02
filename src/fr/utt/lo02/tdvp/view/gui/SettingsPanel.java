package fr.utt.lo02.tdvp.view.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsPanel extends VBox {
    private static SettingsPanel instance = new SettingsPanel();

    private static enum Step {
        PHYSICAL_PLAYERS,
        VIRTUAL_PLAYERS,
        LAYOUT,
        VARIANT,
        PHYSICAL_PLAYERS_NAME_1,
        PHYSICAL_PLAYERS_NAME_2,
        PHYSICAL_PLAYERS_NAME_3
    };

    private Step step;
    private Button backButton, nextButton;
    private VBox settingsPane;

    // Settings value
    private int chosenPhysicalPlayersCount, chosenVirtualPlayersCount, chosenLayout, chosenVariant;
    private String chosenPlayerName1, chosenPlayerName2, chosenPlayerName3;

    public static SettingsPanel getInstance() {
        return instance;
    }

    private void init() {
        this.chosenPhysicalPlayersCount = 1;
        this.chosenVirtualPlayersCount = 0;
        this.chosenLayout = -1;
        this.chosenVariant = -1;
        this.chosenPlayerName1 = "";
        this.chosenPlayerName2 = "";
        this.chosenPlayerName3 = "";
    }

    private SettingsPanel() {
        this.init();

        // Title
        Label title = new Label("Shape Up !");
        title.setStyle("-fx-font-size: 60px");

        // Settings pane
        settingsPane = new VBox();
        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.getStyleClass().add("settings-panel");
        VBox.setMargin(settingsPane, new Insets(40, 0, 40, 0));

        // Buttons pane
        HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(60);
        this.backButton = new Button("RETOUR");
        this.backButton.getStyleClass().add("large-padding");
        this.backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch(step) {
                    case PHYSICAL_PLAYERS:
                        // Go back to the home panel
                        Window.setPanel(HomePanel.getInstance());
                        init();
                        setStep(Step.PHYSICAL_PLAYERS);
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
        this.nextButton = new Button("SUIVANT");
        this.nextButton.getStyleClass().add("large-padding");
        this.nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
                        if (chosenPhysicalPlayersCount == 1) {
                            Window.setPanel(GamePanel.getInstance());
                        }
                        else {
                            setStep(Step.PHYSICAL_PLAYERS_NAME_2);
                        }
                        break;
                    case PHYSICAL_PLAYERS_NAME_2:
                        if (chosenPhysicalPlayersCount == 2) {
                            Window.setPanel(GamePanel.getInstance());
                        }
                        else {
                            setStep(Step.PHYSICAL_PLAYERS_NAME_3);
                        }
                        break;
                    case PHYSICAL_PLAYERS_NAME_3:
                        Window.setPanel(GamePanel.getInstance());
                        break;
                }
            }
        });
        buttonsPane.getChildren().addAll(this.backButton, this.nextButton);

        this.getChildren().addAll(title, settingsPane, buttonsPane);
        this.setAlignment(Pos.CENTER);

        this.setStep(Step.PHYSICAL_PLAYERS);
    }

    private void setStep(Step step) {
        this.step = step;

        Label label = new Label();
        label.setStyle("-fx-font-size: 40px; -fx-padding: 0 0 40px 0");
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);

        switch(step) {
            case PHYSICAL_PLAYERS:
                label.setText("Nombre de joueurs réels");
                ImageButton[] physicalPlayersButtons = {
                    new ImageButton("icons/user" + (chosenPhysicalPlayersCount >= 1 ? "-selected" : "") + ".png"),
                    new ImageButton("icons/user" + (chosenPhysicalPlayersCount >= 2 ? "-selected" : "") + ".png"),
                    new ImageButton("icons/user" + (chosenPhysicalPlayersCount >= 3 ? "-selected" : "") + ".png"),
                };
                physicalPlayersButtons[0].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chosenPhysicalPlayersCount = 1;
                        physicalPlayersButtons[1].changeImage("icons/user.png");
                        physicalPlayersButtons[2].changeImage("icons/user.png");
                    }
                });
                physicalPlayersButtons[1].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chosenPhysicalPlayersCount = 2;
                        physicalPlayersButtons[1].changeImage("icons/user-selected.png");
                        physicalPlayersButtons[2].changeImage("icons/user.png");
                    }
                });
                physicalPlayersButtons[2].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chosenPhysicalPlayersCount = 3;
                        physicalPlayersButtons[1].changeImage("icons/user-selected.png");
                        physicalPlayersButtons[2].changeImage("icons/user-selected.png");
                    }
                });
                pane.getChildren().addAll(physicalPlayersButtons);
                break;
            case VIRTUAL_PLAYERS:
                label.setText("Nombre de joueurs virtuels");
                ImageButton[] virtualPlayersButtons = {
                    new ImageButton("icons/user" + (chosenVirtualPlayersCount >= 1 ? "-selected" : "") + ".png"),
                    new ImageButton("icons/user" + (chosenVirtualPlayersCount >= 2 ? "-selected" : "") + ".png"),
                };
                virtualPlayersButtons[0].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (chosenVirtualPlayersCount == 1) {
                            chosenVirtualPlayersCount = 0;
                            virtualPlayersButtons[0].changeImage("icons/user.png");
                            virtualPlayersButtons[1].changeImage("icons/user.png");
                        }
                        else {
                            chosenVirtualPlayersCount = 1;
                            virtualPlayersButtons[0].changeImage("icons/user-selected.png");
                            virtualPlayersButtons[1].changeImage("icons/user.png");
                        }
                    }
                });
                virtualPlayersButtons[1].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chosenVirtualPlayersCount = 2;
                        virtualPlayersButtons[0].changeImage("icons/user-selected.png");
                        virtualPlayersButtons[1].changeImage("icons/user-selected.png");
                    }
                });
                pane.getChildren().addAll(virtualPlayersButtons);
                break;
            case LAYOUT:
                label.setText("Choix du plateau");
                ImageButton[] layoutButtons = {
                    new ImageButton("icons/layout-rectangle" + (chosenLayout == 0 ? "-selected" : "") + ".png"),
                    new ImageButton("icons/layout-circle" + (chosenLayout == 1 ? "-selected" : "") + ".png"),
                };
                layoutButtons[0].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chosenLayout = 0;
                        layoutButtons[0].changeImage("icons/layout-rectangle-selected.png");
                        layoutButtons[1].changeImage("icons/layout-circle.png");
                    }
                });
                layoutButtons[1].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chosenLayout = 1;
                        layoutButtons[0].changeImage("icons/layout-rectangle.png");
                        layoutButtons[1].changeImage("icons/layout-circle-selected.png");
                    }
                });
                pane.getChildren().addAll(layoutButtons);
                break;
            case VARIANT:
                label.setText("Choix de la variante");
                ImageButton[] variantButtons = {
                    new ImageButton("buttons/variant-random-switch" + (chosenLayout == 0 ? "-selected" : "") + ".png"),
                    new ImageButton("buttons/variant-second-chance" + (chosenLayout == 1 ? "-selected" : "") + ".png"),
                };
                variantButtons[1].setStyle("-fx-border-width: 45px 0 0 0");
                variantButtons[0].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(chosenVariant == 0) {
                            chosenVariant = -1;
                            variantButtons[0].changeImage("buttons/variant-random-switch.png");
                            variantButtons[1].changeImage("buttons/variant-second-chance.png");
                        }
                        else {
                            chosenVariant = 0;
                            variantButtons[0].changeImage("buttons/variant-random-switch-selected.png");
                            variantButtons[1].changeImage("buttons/variant-second-chance.png");
                        }
                    }
                });
                variantButtons[1].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(chosenVariant == 1) {
                            chosenVariant = -1;
                            variantButtons[0].changeImage("buttons/variant-random-switch.png");
                            variantButtons[1].changeImage("buttons/variant-second-chance.png");
                        }
                        else {
                            chosenVariant = 1;
                            variantButtons[0].changeImage("buttons/variant-random-switch.png");
                            variantButtons[1].changeImage("buttons/variant-second-chance-selected.png");
                        }
                    }
                });
                pane.getChildren().addAll(variantButtons);
                break;
            case PHYSICAL_PLAYERS_NAME_1:
                label.setText("Nom du joueur 1");
                break;
            case PHYSICAL_PLAYERS_NAME_2:
                label.setText("Nom du joueur 2");
                break;
            case PHYSICAL_PLAYERS_NAME_3:
                label.setText("Nom du joueur 3");
                break;
        }

        settingsPane.getChildren().clear();
        settingsPane.getChildren().addAll(label, pane);
    }
}
