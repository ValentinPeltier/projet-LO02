package fr.utt.lo02.tdvp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.layout.LayoutCircle;
import fr.utt.lo02.tdvp.model.layout.LayoutRectangle;
import fr.utt.lo02.tdvp.model.player.PhysicalPlayer;
import fr.utt.lo02.tdvp.model.player.Player;
import fr.utt.lo02.tdvp.model.player.VirtualPlayerEasy;
import fr.utt.lo02.tdvp.model.variant.Variant;
import fr.utt.lo02.tdvp.model.variant.VariantRandomSwitch;
import fr.utt.lo02.tdvp.model.variant.VariantSecondChance;

public class Settings extends Observable {
    private static Settings instance = new Settings();

    private Variant variant;
    private Layout layout;
    private List<Player> players = new ArrayList<Player>();

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {}

    public int getPhysicalPlayersCount() {
        int count = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) instanceof PhysicalPlayer) {
                count++;
            }
        }
        return count;
    }

    public int getVirtualPlayersCount() {
        return players.size() - getPhysicalPlayersCount();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Variant getVariant() {
        return variant;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setVariant(int variant) {
        // Set the variant
        switch (variant) {
            case 1:
                this.variant = new VariantRandomSwitch();
                break;
            case 2:
                this.variant = new VariantSecondChance();
                break;
            default:
                // Create a variant that never executes and does nothing
                this.variant = new Variant() {
                    public boolean shouldExecute(int round, int playerIndex) {
                        return false;
                    }
                    public void execute(Player player) {}
                    public void reset() {}
                    @Override
                    public void makeChange() {}
                };
                break;
        }

        GameManager.getInstance().notifyObservers(Events.AskPhysicalPlayersNumber);
    }

    public void setPhysicalPlayers(int count) {
        // Create physical players
        for (int i = 0; i < count; i++) {
            players.add(new PhysicalPlayer());
        }

        GameManager.getInstance().notifyObservers(Events.AskPlayerName1);
    }

    public void setPlayerName(int playerIndex, String name) {
        players.get(playerIndex).setName(name);

        if (playerIndex == 1 && players.size() > 1) {
            GameManager.getInstance().notifyObservers(Events.AskPlayerName2);
        }
        else if (playerIndex == 2 && players.size() > 2) {
            GameManager.getInstance().notifyObservers(Events.AskPlayerName3);
        }
        else if (playerIndex == players.size()) {
            GameManager.getInstance().notifyObservers(Events.AskVirtualPlayerSettings);
        }
    }

    public void setVirtualPlayers(int count) {
        for (int i = 0; i < count; i++) {
            players.add(new VirtualPlayerEasy());
        }

        GameManager.getInstance().notifyObservers(Events.AskLayoutShape);
    }

    public void setLayoutShape(int layoutShape) {
        // Create the layout
        switch (layoutShape) {
            case 1:
                this.layout = new LayoutRectangle();
                break;
            case 2:
                this.layout = new LayoutCircle();
                break;
        }

        GameManager.getInstance().playGame();
    }
}
