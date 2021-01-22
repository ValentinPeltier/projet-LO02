package fr.utt.lo02.tdvp.model.panel;

import fr.utt.lo02.tdvp.controller.PopupPanelController;

public class PopupPanel {
    private static PopupPanel instance = new PopupPanel();

    public static PopupPanel getInstance() {
        return instance;
    }

    private PopupPanel() {
        PopupPanelController.getInstance().init();
    }

    public void init() {}
}
