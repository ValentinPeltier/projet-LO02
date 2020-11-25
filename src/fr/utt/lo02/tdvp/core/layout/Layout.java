package fr.utt.lo02.tdvp.core.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.utt.lo02.tdvp.core.Card;

public abstract class Layout {
    protected Map<Location, Card> locations = new HashMap<Location, Card>();


    public boolean placeCard(int x, int y, Card card, int offsetX, int offsetY) {
    	//TODO
    	return false;
    }

    public boolean moveCard(int x1, int y1, int x2, int y2)
    {
    	//TODO
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
    	for (int y = minY; y <= maxY; y++) {
    		System.out.print("\t "+(char)(y+65));
		}

        // Display grid row by row
    	for (int x = minX; x <= maxX; x++) {
            System.out.print("\n\n" + x + "\t");

    		for(int y = minY; y <= maxY; y++) {
                Location currentLocation = new Location(x, y);
                boolean locationExists = this.locations.containsKey(currentLocation);
    			Card card = this.locations.get(currentLocation);

                if (locationExists) {
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
    }
}
