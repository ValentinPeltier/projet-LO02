package fr.utt.lo02.tdvp.controller;

import fr.utt.lo02.tdvp.view.gui.PopupPanelView;
import fr.utt.lo02.tdvp.view.gui.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Represents the controller of the popup panel.
 * It assign an action listener to the close button of the view.
 * This class uses the singleton design pattern.
 */
public class PopupPanelController {
    /**
     * The unique instance of the class.
     * @see #getInstance()
     */
    private static PopupPanelController instance = new PopupPanelController();

    /**
     * Returns the unique instance of the class.
     * @return The unique instance of the class
     */
    public static PopupPanelController getInstance() {
        return instance;
    }

    /**
     * Instantiates a new PopupPanelController object.
     * Does nothing.
     */
    private PopupPanelController() {}

    /**
     * Initialize the popup panel by adding action listeners to the view.
     */
    public void init() {
        PopupPanelView view = PopupPanelView.getInstance();

        view.getCloseButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window.hidePopup();
            }
        });
    }
}
