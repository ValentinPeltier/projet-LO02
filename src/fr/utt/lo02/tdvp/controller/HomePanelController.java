package fr.utt.lo02.tdvp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import fr.utt.lo02.tdvp.view.gui.HomePanelView;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;
import fr.utt.lo02.tdvp.view.gui.Window;

/**
 * Represents the controller of the home panel.
 * It assign an action listener to the play button of the view.
 * This class uses the singleton design pattern.
 */
public class HomePanelController {
    /**
     * The unique instance of the class.
     * @see #getInstance()
     */
    private static HomePanelController instance = new HomePanelController();

    /**
     * Returns the unique instance of the class.
     * @return The unique instance of the class
     */
    public static HomePanelController getInstance() {
        return instance;
    }

    /**
     * Instantiates a new HomePanelController object.
     * Does nothing.
     */
    private HomePanelController() {}

    /**
     * Initialize the home panel by adding action listeners to the view.
     */
    public void init() {
        HomePanelView view = HomePanelView.getInstance();

        view.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window.setPanel(SettingsPanelView.getInstance());
            }
        });

    }
}
