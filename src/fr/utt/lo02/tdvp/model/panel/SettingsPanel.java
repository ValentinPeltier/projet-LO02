package fr.utt.lo02.tdvp.model.panel;

import java.util.Observable;

import fr.utt.lo02.tdvp.controller.SettingsPanelController;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;

@SuppressWarnings("deprecation")
public class SettingsPanel extends Observable {
    private static SettingsPanel instance = new SettingsPanel();

    public static SettingsPanel getInstance() {
        return instance;
    }

    private SettingsPanel() {
        SettingsPanelController.getInstance().init();
        addObserver(SettingsPanelView.getInstance());
    }

    public void init() {}
}
