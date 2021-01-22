package fr.utt.lo02.tdvp.model.panel;


import fr.utt.lo02.tdvp.controller.HomePanelController;

public class HomePanel {
    private static HomePanel instance = new HomePanel();

    public static HomePanel getInstance() {
        return instance;
    }

    private HomePanel() {
        HomePanelController.getInstance().init();
    }

    public void init() {
    }
}
