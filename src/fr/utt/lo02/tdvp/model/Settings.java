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

/**
* The class that ask and contains all the informations on the game 
* Is Called by different classes to get different parameters as variant, layout, players...
* @see Observable
*/

@SuppressWarnings("deprecation")
public class Settings extends Observable {
	
	/**
     * The single instance of the settings class, in order to make the singleton work
     */
    private static Settings instance = new Settings();

    /**
     * The chosen variant for the game
     */
    private Variant variant;
    
    /**
     * The Board of the game
     */
    private Layout layout;
    
    /**
     * The players List of the game
     */
    private List<Player> players = new ArrayList<Player>();

    /**
     * @return return the instance the settings class, in order to make the singleton work
     */
    public static Settings getInstance() {
        return instance;
    }

    /**
     * Constructor of the class
     */
    private Settings() {}

    /**
     * Get the number of physical player, already set by the user
     * @return the current number of physical player
     */
    public int getPhysicalPlayersCount() {
        int count = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) instanceof PhysicalPlayer) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get the number of virtual player
     * @return the number of virtual player
     */
    public int getVirtualPlayersCount() {
        return players.size() - getPhysicalPlayersCount();
    }

    /**
     * @return the Players List
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return the game Variant
     */
    public Variant getVariant() {
        return variant;
    }

    /**
     * @return the Game Layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Set the variant of the game, depending on the choice of the user, set in the view
     * @param the variant number, set by the user 
     */
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

    /**
     * Set the different Physical players and ask their names in the view
     * @param the number of players set by the user
     */
    public void setPhysicalPlayers(int count) {
        // Create physical players
        for (int i = 0; i < count; i++) {
            players.add(new PhysicalPlayer());
        }

        GameManager.getInstance().notifyObservers(Events.AskPlayerName1);
    }

    /**
     * Set the Player name, depending on the playerIndex
     * @param the index of the player in the Players List
     * @param the name of the player 
     */
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

    /**
     * Set the different Virtual Players of the game
     * @param the number of players set by the user
     */
    public void setVirtualPlayers(int count) {
        for (int i = 0; i < count; i++) {
            players.add(new VirtualPlayerEasy());
        }

        GameManager.getInstance().notifyObservers(Events.AskLayoutShape);
    }

    /**
     * Set the shape of the layout of the game
     * @param the number of the choice set by the user, defining the layout shape
     */
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
