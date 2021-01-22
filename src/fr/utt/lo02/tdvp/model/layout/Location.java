package fr.utt.lo02.tdvp.model.layout;

import java.util.Objects;

/**
 * Util class to store x and y for a card location
 **/

public class Location {
	
	/**
	 * the stored x
	 **/
    private int x;

    /**
	 * the stored y
	 **/
    private int y;
    
    
    /**
	 * @return the stored x
	 **/
    public int getX() {
        return this.x;
    }

    /**
	 * @return the stored x
	 **/
    public int getY() {
        return this.y;
    }

    /**
	 * store a wanted x
	 * @param the given x we want to store
	 **/
    public void setX(int x) {
        this.x = x;
    }

    /**
	 * store a wanted y
	 * @param the given y we want to store
	 **/
    public void setY(int y) {
        this.y = y;
    }

    /**
	 * Constructor of the class
	 * @param the given x
	 * @param the given y
	 **/
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a string representation of the location
     * @return a string representation of the location
     */
    @Override
    public String toString() {
        return (char)(this.x + 'A') + "" + this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
	 * Check if the given object is a location, and if the Location equals to this location
	 * @param a given Object
	 **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Location other = (Location) obj;
        return x == other.x && y == other.y;
    }
}
