package fr.utt.lo02.tdvp.model.layout;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;

/**
 * The mother class of the differents layouts
 * The class extends a Observable class
 * @see Observable
 */

@SuppressWarnings("deprecation")
public abstract class Layout extends Observable {
	
	/**
	 * DIfferent types of layouts
	 */
    public static enum Name {
        Circle,
        Rectangle,
    }

    /**
     * The actual layout
     */
    protected Map<Location, Card> locations = new HashMap<Location, Card>();
    
    /**
     * x index of the layout
     **/
    protected int x;
    /**
     * y index of the layout
     **/
    protected int y;

    /**
     * Check if the given position have an adjacent card
     * @return {@code false} if the card is Adjacent to another {@code true} otherwise
     * @param x, is the x position of the given card
     * @param y, is the y position of the given card
     **/
    public boolean isCardAjacent(int x, int y) {
    	boolean noAdjacentCard = !isEmpty()
        && getCardAt(x - 1, y) == null
        && getCardAt(x + 1, y) == null
        && getCardAt(x, y - 1) == null
        && getCardAt(x, y + 1) == null;

    	return !noAdjacentCard;
    }
    
    
    /**
     * Give a random location on the board
     * @return A random Location
     **/
    public Location getRandomLocation() {
        Location randomLocation;
        Random randomGenerator = new Random();
        Object[] locations = this.locations.keySet().toArray();

        // Get random location where a card is placed
        do {
            randomLocation = (Location) locations[randomGenerator.nextInt(locations.length)];
        } while(this.locations.get(randomLocation) == null);

        return randomLocation;
    }

    /**
     * Indicate if the specified location exists in the layout
     * @param x X coordinate of the specified location
     * @param y Y coordinate of the specified location
     * @return {@code true} if the specified location exists in this layout, {@code false} otherwise
     */

    public boolean locationExists(int x, int y) {
        Location location = new Location(x, y);

        if (this.locations.containsKey(location)) {
            return true;
        }

        return false;
    }

    /**
     * Returns the card placed at the specified (x, y) coordinates.
     * A null value will be returned if the location doesn't exists or if no card is placed at these coordinates.
     * @param x X coordinate of the specified location
     * @param y Y coordinate of the specified location
     * @return The card at the specified coordinates
     */
    public Card getCardAt(int x, int y) {
        Location location = new Location(x, y);
        return getCardAt(location);
    }

    /**
     * Returns the card placed at the specified (x, y) coordinates of a given Location
     * A null value will be returned if the location doesn't exists or if no card is placed at these coordinates.
     * @param a given location
     * @return The card at the specified coordinates
     */
    public Card getCardAt(Location location) {
        return this.locations.get(location);
    }

    /**
     * Set a Card at the specified x and y 
     * @param x X coordinate of the specified location
     * @param y Y coordinate of the specified location
     * @return The card to put at the specified coordinates
     */
    public boolean setCardAt(int x, int y, Card card) {
        Location location = new Location(x, y);
        return setCardAt(location, card);
    }

    /**
     * Set a Card at a specified Location
     * @param the specified Location
     * @return The card to put at the specified Location
     */
    public boolean setCardAt(Location location, Card card) {
        // If specified location is on the layout
        if (this.locations.containsKey(location)) {
            // Place the card
            this.locations.put(location, card);

            setChanged();
            notifyObservers();

            return true;
        }

        return false;
    }

    /**
     * Try to place a card at specified coordinates
     * @param x X coordinate of the specified location
     * @param y Y coordinate of the specified location
     * @param card The card to place at these coordinates
     * @return {@code true} if the card has been placed, {@code false} if it cannot be placed
     */
    public boolean placeCard(int x, int y, Card card) {
        // Check if the specified location exists
        // or if a card is already at this location
        if (!this.locationExists(x, y) || this.getCardAt(x, y) != null) {
            return false;
        }

        return this.setCardAt(x, y, card);
    }

    /**
     * Move a card on the layout
     * @param x1 X coordinate of the card to move
     * @param y1 Y coordinate of the card to move
     * @param x2 X coordinate destination
     * @param y2 Y coordinate destination
     * @return {@code true} if the card has been moved, {@code false} otherwise
     */
    public boolean moveCard(int x1, int y1, int x2, int y2) {
        // Check if the 2 locations exist
        if (!this.locationExists(x1, y1) || !this.locationExists(x2, y2)) {
            return false;
        }

        // Check if there is a card in the originate coordinates
        Card originCard = this.getCardAt(x1, y1);
        if (originCard == null) {
            return false;
        }

        Card destinationCard = this.getCardAt(x2, y2);
        this.setCardAt(x1, y1, destinationCard);
        this.setCardAt(x2, y2, originCard);

        return true;
    }

    /**
     * Display the Board Properly
     */
    public void display() {
        GameManager.getInstance().notifyObservers(Events.DisplayLayout);
    }

    /**
     * Count the number of cards on the board
     * @return the number of cards on the board
     */
    public int countCards() {
        int count = 0;

        for (Card card: this.locations.values()) {
            if (card != null) {
                count++;
            }
        }

        return count;
    }

    /**
     * Check if the board is empty
     * @return {@code true} there is no card on the board, {@code false} otherwise
     */
    public boolean isEmpty() {
        return countCards() == 0;
    }

    /**
     * Check if the board is full
     * @return {@code true} the Board is full, {@code false} otherwise
     */
    public boolean isFull() {
        return countCards() == this.locations.size();
    }

    //Accept Visitor => CountPoints
    
    /**
     * Accept the layout visitor in order to count points
     * @param The layout visiotr singleton
     **/
    public void countPointsAccept(LayoutVisitor visitor) {
    	visitor.countPointsVisit(this);
    }

    /**
     * Return the last used x
     * @return the current x
     **/
    public int getX() {
    	return this.x;
    }

    /**
     * Return the last used y
     * @return the current y
     **/
    public int getY() {
    	return this.y;
    }

    /**
     * Return the actual board
     * @return the board, a Map of Locations and cards
     **/
    public Map<Location,Card> getLocations() {
    	return this.locations;
    }

    /**Reset the layout**/
    public abstract void reset();

    /**
     * Move the whole grid left or right
     * @return {@code false} if the movement is impossible, {@code true} if the movement has been made
     * @param offset Offset of the movement : -1 for right and +1 for left
     */
    public abstract boolean moveHorizontally(int offset);//On X

    /**
     * Move the whole grid up or down
     * @return {@code false} if the movement is impossible, {@code true} if the movement has been made
     * @param offset Offset of the movement : 1 for right and -1 for left
     */
    public abstract boolean moveVertically(int offset);//On Y
}
