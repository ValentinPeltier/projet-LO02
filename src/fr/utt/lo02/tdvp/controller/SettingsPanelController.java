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

/**
 * Represents the controller of the settings panel.
 * It assign action listeners to the buttons.
 * This class uses the singleton design pattern.
 */
@SuppressWarnings("deprecation")
public class SettingsPanelController implements Observer {
    /**
     * The unique instance of the class.
     * @see #getInstance()
     */
    private static SettingsPanelController instance = new SettingsPanelController();

    /**
     * The instance of the settings panel view.
     */
    private SettingsPanelView view = SettingsPanelView.getInstance();

    /**
     * The instance of the settings.
     */
    private Settings settings = Settings.getInstance();

    /**
     * Returns the unique instance of the class.
     * @return The unique instance of the class
     */
    public static SettingsPanelController getInstance() {
        return instance;
    }

    /**
     * Instantiates a new SettingsPanelController object.
     * Start observing appropriate objects.
     */
	private SettingsPanelController() {
        GameManager.getInstance().addObserver(this);
        settings.addObserver(this);
    }

    /**
     * Change the action listener of "back" and "next" buttons according to the event value of arg.
     * @param o The observable object that called notifyObservers()
     * @param arg The argument passed in the notifyObservers() method
     * @see Observer
     * @see Observable
     */
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

    /**
     * Initialize the settings panel by adding action listeners to the view.
     */
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
