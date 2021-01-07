package fr.utt.lo02.tdvp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import fr.utt.lo02.tdvp.view.gui.HomePanelView;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;
import fr.utt.lo02.tdvp.view.gui.Window;

public class HomePanelController {
    private static HomePanelController instance = new HomePanelController();

    public static HomePanelController getInstance() {
        return instance;
    }

    private HomePanelController() {}

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
