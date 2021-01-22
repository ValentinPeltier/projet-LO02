package fr.utt.lo02.tdvp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Observable;
import java.util.Observer;

import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.view.gui.HomePanelView;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;
import fr.utt.lo02.tdvp.view.gui.Window;

@SuppressWarnings("deprecation")
public class SettingsPanelController implements Observer {
    private static SettingsPanelController instance = new SettingsPanelController();

    SettingsPanelView view = SettingsPanelView.getInstance();
    Settings settings = Settings.getInstance();

    public static SettingsPanelController getInstance() {
        return instance;
    }

	private SettingsPanelController() {
        GameManager.getInstance().addObserver(this);
        settings.addObserver(this);
    }

    @SuppressWarnings("incomplete-switch")
	public void update(Observable o, Object arg) {
        if (arg instanceof Events) {
            switch((Events) arg) {
                case AskVariant:
                    // Handle next button action
                    view.getNextButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            settings.setVariant(Integer.parseInt(view.getInput()));
                        }
                    });
                    break;
                case AskPhysicalPlayersNumber:
                    // Handle next button action
                    view.getNextButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            settings.setPhysicalPlayers(Integer.parseInt(view.getInput()));
                        }
                    });
                    break;
                case AskPlayerName1:
                    // Handle next button action
                    view.getNextButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            settings.setPlayerName(1, view.getInput());
                        }
                    });
                    break;
                case AskPlayerName2:
                    // Handle next button action
                    view.getNextButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            settings.setPlayerName(2, view.getInput());
                        }
                    });
                    break;
                case AskPlayerName3:
                    // Handle next button action
                    view.getNextButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            settings.setPlayerName(3, view.getInput());
                        }
                    });
                    break;
                case AskVirtualPlayerSettings:
                    // Handle next button action
                    view.getNextButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            settings.setVirtualPlayers(Integer.parseInt(view.getInput()));
                        }
                    });
                    break;
            }
        }
    }

    public void init() {
        // Handle back button action
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window.setPanel(HomePanelView.getInstance());
            }
        });
    }
}
