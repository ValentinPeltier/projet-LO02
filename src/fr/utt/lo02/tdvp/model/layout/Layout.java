package fr.utt.lo02.tdvp.core.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import fr.utt.lo02.tdvp.core.Card;

public abstract class Layout {

    protected Map<Location, Card> locations = new HashMap<Location, Card>();
    protected int x;
    protected int y;

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

    public Card getCardAt(Location location) {
        return this.locations.get(location);
    }

    public boolean setCardAt(int x, int y, Card card) {
        Location location = new Location(x, y);
        return setCardAt(location, card);
    }

    public boolean setCardAt(Location location, Card card) {
        // If specified location is on the layout
        if (this.locations.containsKey(location)) {
            // Place the card
            this.locations.put(location, card);
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

    public void display() {
        // Determine min and max coordinates
        int minX = 0, minY = 0, maxX = 0, maxY = 0;
        Iterator<Location> mapIterator = this.locations.keySet().iterator();

        while (mapIterator.hasNext()) {
            Location location = mapIterator.next();

            if (location.getX() < minX) {
                minX = location.getX();
            }

            if (location.getY() < minY) {
                minX = location.getX();
            }

            if (location.getX() > maxX) {
                maxX = location.getX();
            }

            if (location.getY() > maxY) {
                maxY = location.getY();
            }
        }

        // Display the letters
        for (int x = minX; x <= maxX; x++) {
            System.out.print("\t " + (char)('A' + x));
        }

        // Display grid row by row
        for (int y = minY; y <= maxY; y++) {
            System.out.print("\n\n" + y + "\t");

            for(int x = minX; x <= maxX; x++) {
                boolean locationExists = this.locationExists(x, y);

                if (locationExists) {
                    Card card = this.getCardAt(x, y);

                    if (card != null) {
                        System.out.print(card + "\t");
                    }
                    else {
                        System.out.print(" x \t");
                    }
                }
                else {
                    System.out.print("   \t");
                }
            }
        }

        System.out.println("\n");
    }

    public int countCards() {
        int count = 0;

        for (Card card: this.locations.values()) {
            if (card != null) {
                count++;
            }
        }

        return count;
    }

    public boolean isEmpty() {
        return countCards() == 0;
    }

    public boolean isFull() {
        return countCards() == this.locations.size();
    }

    //Accept Visitor => CountPoints
    public void countPointsAccept(LayoutVisitor visitor) {
    	visitor.countPointsVisit(this);
    }

    public int getX() {
    	return this.x;
    }

    public int getY() {
    	return this.y;
    }

    public Map<Location,Card> getLocations() {
    	return this.locations;
    }

    public abstract void reset();
}
