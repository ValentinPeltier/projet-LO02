package fr.utt.lo02.tdvp.model.panel;

import java.util.Observable;

import fr.utt.lo02.tdvp.controller.SettingsPanelController;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;

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

    // public void nextStep() {
    //     Settings settings = Settings.getInstance();

    //     switch(step) {
    //         case PHYSICAL_PLAYERS:
    //             if (settings.isPhysicalPlayersCountValid()) {
    //                 step = Step.VIRTUAL_PLAYERS;
    //             }
    //             else {
    //                 Window.displayPopup("Nombre de joueurs réels invalide");
    //             }
    //             break;
    //         case VIRTUAL_PLAYERS:
    //             if (settings.isVirtualPlayersCountValid()) {
    //                 step = Step.LAYOUT;
    //             }
    //             else {
    //                 Window.displayPopup("Nombre de joueurs virtuels invalide");
    //             }
    //             break;
    //         case LAYOUT:
    //             if (settings.isLayoutValid()) {
    //                 step = Step.VARIANT;
    //             }
    //             else {
    //                 Window.displayPopup("Choix du plateau invalide");
    //             }
    //             break;
    //         case VARIANT:
    //             Settings.getInstance().setVariant(SettingsPanelView.getInstance().getChosenVariant());
    //             if (settings.isVariantValid()) {
    //                 step = Step.PHYSICAL_PLAYERS_NAME_1;
    //             }
    //             else {
    //                 Window.displayPopup("Choix de la variante invalide");
    //             }
    //             break;
    //         case PHYSICAL_PLAYERS_NAME_1:
    //             if (Settings.getInstance().getPhysicalPlayersCount() == 1) {
    //                 Window.setPanel(GamePanelView.getInstance());
    //             }
    //             else {
    //                 step = Step.PHYSICAL_PLAYERS_NAME_2;
    //             }
    //             break;
    //         case PHYSICAL_PLAYERS_NAME_2:
    //             if (Settings.getInstance().getPhysicalPlayersCount() == 2) {
    //                 Window.setPanel(GamePanelView.getInstance());
    //             }
    //             else {
    //                 step = Step.PHYSICAL_PLAYERS_NAME_3;
    //             }
    //             break;
    //         case PHYSICAL_PLAYERS_NAME_3:
    //             Window.setPanel(GamePanelView.getInstance());
    //             break;
    //     }

    //     setChanged();
    //     notifyObservers();
    // }
}
