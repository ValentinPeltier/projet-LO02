package fr.utt.lo02.tdvp.controller;

import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;

/**
 * Main game controller in the MVC architecture.
 * It makes a link between the views and the models.
 * This class uses the singleton design pattern.
 */
public class Controller {
    /**
     * The unique instance of the class.
     * @see #getInstance()
     */
    private static Controller instance = new Controller();

    /**
     * The settings instance.
     */
    private Settings settings = Settings.getInstance();

    /**
     * Returns the unique instance of the class.
     * @return The unique instance of the class
     */
    public static Controller getInstance() {
        return instance;
    }

    /**
     * Instantiates a new Controller object.
     */
	private Controller() {}

    /**
     * Sets the game variant.
     * @param answer The user answer. Should be 1 (random switch) or 2 (second chance). Otherwise it will be ignored.
     */
	public void setVariant(int answer) {
		settings.setVariant(answer);
	}

    /**
     * Sets the game board layout.
     * @param answer The user answer. Should be 1 (rectangle) or 2 (circle).
     */
	public void setLayoutShape(int answer) {
		settings.setLayoutShape(answer);
	}

    /**
     * Sets the number of physical players.
     * @param answer The user answer. Should be in the range [1-3].
     */
	public void setPhysicalPlayersNumber(int answer) {
		settings.setPhysicalPlayers(answer);
	}

    /**
     * Sets the name of a player.
     * @param playerIndex The index of the player
     * @param name The name of the player
     */
	public void setPlayerName(int playerIndex, String name) {
		settings.setPlayerName(playerIndex, name);
    }

    /**
     * Sets the number of virtual players.
     * @param count The number of virtual players. count + physicalPlayersCount should be in range [2 ; 3]
     */
    public void setVirtualPlayer(int count) {
		settings.setVirtualPlayers(count);
	}

    /**
     * Places the card to a specified location.
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     * @param card The card to place at this location
     * @return {@code true} if the card has been placed, {@code false} if it cannot be placed here
     */
	public boolean placeCard(int x, int y, Card card) {
		return settings.getLayout().placeCard(x, y, card);
	}

    /**
     * Moves a card at a specified location.
     * @param x1 The x coordinate of the originate location
     * @param y1 The y coordinate of the originate location
     * @param x2 The x coordinate of the destination location
     * @param y2 The y coordinate of the destination location
     * @return {@code true} if the card has been placed, {@code false} if it cannot be placed here
     */
	public boolean moveCards(int x1, int y1, int x2, int y2) {
		return settings.getLayout().moveCard(x1, y1, x2, y2);
	}

	public void askPlaceCard() {
		GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).placeCard();
	}

	public void askMoveCard() {
		GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).moveCard();
	}

	public void endTurn() {
		GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).endTurn();
	}

	public boolean moveHorizontally(int offset) {
		return settings.getLayout().moveHorizontally(offset);
	}

	public boolean moveVertically(int offset) {
		return settings.getLayout().moveVertically(offset);
	}

	public void variantSecondChance() {
		settings.getVariant().makeChange();
	}
}
