package fr.utt.lo02.tdvp.core.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.utt.lo02.tdvp.core.Card;

public abstract class Layout {
    protected Map<Location, Card> locations = new HashMap<Location, Card>();

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
        return this.locations.get(location);
    }

    private boolean setCardAt(int x, int y, Card card) {
        Location location = new Location(x, y);

        // If specified location is on the layout
        if (this.locations.containsKey(location)) {
            // Place the card
            this.locations.put(location, card);
            return true;
        }

        return false;
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

    public boolean isEmpty() {
        for (Card card: this.locations.values()) {
            if (card != null) {
                return false;
            }
        }

        return true;
    }
}
