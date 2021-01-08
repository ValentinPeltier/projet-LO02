package fr.utt.lo02.tdvp.view.gui;

import java.util.Observable;
import java.util.Observer;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.variant.Variant;
import fr.utt.lo02.tdvp.view.gui.utils.ImageButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsPanelView extends VBox implements Observer {
    private static SettingsPanelView instance = new SettingsPanelView();

    private VBox settingsPane;
    private Button backButton;
    private Button nextButton;

    private String input = null;

    public String getInput() {
        String tmp = input;
        input = null;
        return tmp;
    }

    public static SettingsPanelView getInstance() {
        return instance;
    }

    private SettingsPanelView() {
        GameManager.getInstance().addObserver(this);

        // Title
        Label title = new Label("Shape Up !");
        title.setStyle("-fx-font-size: 60px");

        // Settings pane
        settingsPane = new VBox();
        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.getStyleClass().add("settings-panel");

        // Back button
        backButton = new Button("RETOUR");
        backButton.getStyleClass().add("large-padding");

        // Next button
        nextButton = new Button("SUIVANT");
        nextButton.getStyleClass().add("large-padding");

        // Buttons pane
        HBox buttonsPane = new HBox(backButton, nextButton);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(60);

        getChildren().addAll(title, settingsPane, buttonsPane);
        setAlignment(Pos.CENTER);
        setSpacing(40);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Events && o instanceof GameManager) {
            Label label = new Label("");
            label.setStyle("-fx-font-size: 40px; -fx-padding: 0 0 40px 0");
            HBox pane = new HBox();
            pane.setAlignment(Pos.CENTER);

            switch ((Events) arg) {
            	//SETTINGS
                case AskVariant:
                	this.askVariant(label, pane);
                	break;
                case AskPhysicalPlayersNumber:
                	this.askPhysicalPlayersNumber(label, pane);
                	break;
                case AskLayoutShape:
                	this.askLayoutShape(label, pane);
                    break;
                case AskPlayerName1:
                	this.askPhysicalPlayerName(0, label, pane);
                    break;
                case AskPlayerName2:
                    this.askPhysicalPlayerName(1, label, pane);
                    break;
                case AskPlayerName3:
                	this.askPhysicalPlayerName(2, label, pane);
                	break;
                case AskVirtualPlayerSettings:
                	this.askVirtualPlayersNumber(label, pane);
                	break;
            }

            settingsPane.getChildren().clear();
            settingsPane.getChildren().addAll(label, pane);
        }
    }

    public VBox getSettingsPane() {
        return settingsPane;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    private void askVariant(Label label, HBox pane) {
        label.setText("Choix de la variante");
        ImageButton[] variantButtons = {
            new ImageButton("buttons/variant-second-chance" + (input == "2" ? "-selected" : "") + ".png"),
            new ImageButton("buttons/variant-random-switch" + (input == "1" ? "-selected" : "") + ".png"),
        };
        variantButtons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(input == "2") {
                    input = null;
                    variantButtons[0].changeImage("buttons/variant-second-chance.png");
                    variantButtons[1].changeImage("buttons/variant-random-switch.png");
                }
                else {
                    input = "2";
                    variantButtons[0].changeImage("buttons/variant-second-chance-selected.png");
                    variantButtons[1].changeImage("buttons/variant-random-switch.png");
                }
            }
        });
        variantButtons[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(input == "1") {
                    input = null;
                    variantButtons[0].changeImage("buttons/variant-second-chance.png");
                    variantButtons[1].changeImage("buttons/variant-random-switch.png");
                }
                else {
                    input = "1";
                    variantButtons[0].changeImage("buttons/variant-second-chance.png");
                    variantButtons[1].changeImage("buttons/variant-random-switch-selected.png");
                }
            }
        });
        pane.getChildren().addAll(variantButtons);
    }

    private void askPhysicalPlayersNumber(Label label, HBox pane) {
        label.setText("Nombre de joueurs réels");
        ImageButton[] physicalPlayersButtons = {
            new ImageButton("icons/user" + (Settings.getInstance().getPhysicalPlayersCount() >= 1 ? "-selected" : "") + ".png"),
            new ImageButton("icons/user" + (Settings.getInstance().getPhysicalPlayersCount() >= 2 ? "-selected" : "") + ".png"),
            new ImageButton("icons/user" + (Settings.getInstance().getPhysicalPlayersCount() >= 3 ? "-selected" : "") + ".png"),
        };
        for(int i = 0; i < 3; i++) {
            final int fi = i;
            physicalPlayersButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    input = "" + (fi + 1);
                    physicalPlayersButtons[1].changeImage("icons/user" + (fi >= 1 ? "-selected" : "") + ".png");
                    physicalPlayersButtons[2].changeImage("icons/user" + (fi >= 2 ? "-selected" : "") + ".png");
                }
            });
        }
        pane.getChildren().addAll(physicalPlayersButtons);
    }

    private void askLayoutShape(Label label, HBox pane) {
        label.setText("Choix du plateau");
        ImageButton[] layoutButtons = {
            new ImageButton("icons/layout-rectangle" + (input == "1" ? "-selected" : "") + ".png"),
            new ImageButton("icons/layout-circle" + (input == "2" ? "-selected" : "") + ".png"),
        };
        layoutButtons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input = "1";
                layoutButtons[0].changeImage("icons/layout-rectangle-selected.png");
                layoutButtons[1].changeImage("icons/layout-circle.png");
            }
        });
        layoutButtons[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input = "2";
                layoutButtons[0].changeImage("icons/layout-rectangle.png");
                layoutButtons[1].changeImage("icons/layout-circle-selected.png");
            }
        });
        pane.getChildren().addAll(layoutButtons);
    }

    private void askPhysicalPlayerName(int i, Label label, HBox pane) {
        label.setText("Nom du joueur " + (i + 1));
        input = "test " + i;
    }

    private void askVirtualPlayersNumber(Label label, HBox pane) {
        label.setText("Nombre de joueurs virtuels");
        ImageButton[] virtualPlayersButtons = {
            new ImageButton("icons/user" + (input == "1" || input == "2" ? "-selected" : "") + ".png"),
            new ImageButton("icons/user" + (input == "2" ? "-selected" : "") + ".png"),
        };
        for(int i = 0; i < 2; i++) {
            final int fi = i;
            virtualPlayersButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (fi + 1 == Settings.getInstance().getVirtualPlayersCount()) {
                        if (input == "0") {
                            virtualPlayersButtons[0].changeImage("icons/user.png");
                            virtualPlayersButtons[1].changeImage("icons/user.png");
                        }
                        else {
                            Window.displayPopup("NOMBRE DE JOUEURS VIRTUELS", "Vous devez choisir au moins un joueur virtuel");
                        }
                    }
                    else {
                        if (input == "" + (fi + 1)) {
                            virtualPlayersButtons[0].changeImage("icons/user" + (Settings.getInstance().getVirtualPlayersCount() >= 1 ? "-selected" : "") + ".png");
                            virtualPlayersButtons[1].changeImage("icons/user" + (Settings.getInstance().getVirtualPlayersCount() >= 2 ? "-selected" : "") + ".png");
                        }
                        else {
                            Window.displayPopup("NOMBRE DE JOUEURS VIRTUELS", "Vous ne pouvez pas choisir autant de joueurs virtuels");
                        }
                    }
                }
            });
        }
        pane.getChildren().addAll(virtualPlayersButtons);
    }
}
