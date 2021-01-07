package fr.utt.lo02.tdvp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import fr.utt.lo02.tdvp.model.panel.SettingsPanel;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;

public class SettingsPanelController {
    private static SettingsPanelController instance = new SettingsPanelController();

    public static SettingsPanelController getInstance() {
        return instance;
    }

    private SettingsPanelController() {}

    public void init() {
        SettingsPanelView view = SettingsPanelView.getInstance();

        // Handle back button action
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsPanel.getInstance().previousStep();
            }
        });
        // Handle next button action
        view.getNextButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsPanel.getInstance().nextStep();
            }
        });
    }
}
