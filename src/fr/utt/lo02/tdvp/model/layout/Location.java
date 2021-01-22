package fr.utt.lo02.tdvp.model.layout;

import java.util.Objects;

public class Location {
    private int x;

    private int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

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
