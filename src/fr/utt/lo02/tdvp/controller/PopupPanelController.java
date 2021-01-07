package fr.utt.lo02.tdvp.controller;

import fr.utt.lo02.tdvp.view.gui.PopupPanelView;
import fr.utt.lo02.tdvp.view.gui.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PopupPanelController {
    private static PopupPanelController instance = new PopupPanelController();

    public static PopupPanelController getInstance() {
        return instance;
    }

    private PopupPanelController() {}

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
