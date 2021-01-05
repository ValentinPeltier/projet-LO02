package fr.utt.lo02.tdvp.model;

public class Card {
    /**
     * The possible shapes of a card
     */
    public enum Shape {
        SQUARE,
        TRIANGLE,
        CIRCLE,
    }

    /**
     * The possible colors of a card
     */
    public enum Color {
        RED,
        GREEN,
        BLUE,
    }

    /**
     * Is the card filled or hollow ?
     */
    public enum Filled {
        HOLLOW,
        FILLED
    }

    private Shape shape;
    private Color color;
    private Filled filled;

    /**
     * Instantiate a card
     * @param shape the shape of the card
     * @param color the color of the card
     * @param filled the filling of the card
     */
    public Card(Shape shape, Color color, Filled filled) {
        this.shape = shape;
        this.color = color;
        this.filled = filled;
    }

    /**
     * Get the shape of the card
     * @return the shape of the card
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     * Get the color of the card
     * @return the color of the card
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Get the filling of the card
     * @return the filling of the card
     */
    public Filled getFilled() {
        return this.filled;
    }

    /**
     * Parse the card to a string defining the card
     * @return a string defining the card
     */
    @Override
    public String toString() {
        String shape, color, filled;

        // Shape
        switch (this.shape) {
            case SQUARE: shape = "S";
                break;
            case TRIANGLE: shape = "T";
                break;
            case CIRCLE: shape = "C";
                break;
            default: shape = this.shape.toString();
        }

        // Color
        switch (this.color) {
            case RED: color = "R";
                break;
            case GREEN: color = "G";
                break;
            case BLUE: color = "B";
                break;
            default: color = this.color.toString();
        }

        // Filled
        switch (this.filled) {
            case HOLLOW: filled = "H";
                break;
            case FILLED: filled = "F";
                break;
            default: filled = this.color.toString();
        }


        return shape + color + filled;
    }

}
