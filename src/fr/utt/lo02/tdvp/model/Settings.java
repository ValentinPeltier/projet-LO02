package fr.utt.lo02.tdvp.model;

import java.util.Observable;

import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.variant.Variant;

public class Settings extends Observable {
    private static Settings instance = new Settings();

    private Variant.Name variant;
    private int physicalPlayersCount;
    private int virtualPlayersCount;
    private Layout.Name layout;
    
    GameManager gameManager = GameManager.getInstance();

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {}

    public void reset() {
        // Reset all settings
        variant = null;
        physicalPlayersCount = 1;
        virtualPlayersCount = 1;
        layout = null;
        setChanged();
        notifyObservers();
    }

    public boolean setVariant(Variant.Name variantIndex) {
        variant = variantIndex;
        setChanged();
        notifyObservers();
        return true;
    }

    public Variant.Name getVariant() {
        return variant;
    }

    public boolean isVariantValid() {
        return true;
    }

    public boolean setPhysicalPlayersCount(int count) {
        if (count < 1 || count > 3) {
            return false;
        }
        physicalPlayersCount = count;
        gameManager.setPhysicalPlayers(physicalPlayersCount);
        setChanged();
        notifyObservers();
        return true;
    }

    public int getPhysicalPlayersCount() {
        return physicalPlayersCount;
    }

    public boolean isPhysicalPlayersCountValid() {
        return physicalPlayersCount >= 1 && physicalPlayersCount <= 3;
    }

    public boolean setVirtualPlayersCount(int count) {
        // Should be defined
        if (physicalPlayersCount == -1) {
            return false;
        }

        if (count < 0 || physicalPlayersCount + count < 2 || physicalPlayersCount + count > 3) {
            return false;
        }

        virtualPlayersCount = count;
        setChanged();
        notifyObservers();
        return true;
    }

    public int getVirtualPlayersCount() {
        return virtualPlayersCount;
    }

    public boolean isVirtualPlayersCountValid() {
        return virtualPlayersCount >= 0
            && physicalPlayersCount + virtualPlayersCount >= 2
            && physicalPlayersCount + virtualPlayersCount <= 3;
    }

    public boolean setLayout(Layout.Name layoutIndex) {
        layout = layoutIndex;
        setChanged();
        notifyObservers();
        return true;
    }

    public Layout.Name getLayout() {
        return layout;
    }

    public boolean isLayoutValid() {
        return layout != null;
    }
}
